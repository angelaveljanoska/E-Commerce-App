import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import Cart from '../_models/cart';
import Product from '../_models/product';
import { CartService } from '../_services/cart.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  id!: number;
  products!: Product[];
  maxQuantity!: number[];
  sum!: number;
  constructor(private cartService: CartService, private tokenStorageService: TokenStorageService, private toastrService: ToastrService, private router: Router) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.cartService.getCartForUser(user.id).subscribe((cart: Cart) => {
      this.products = cart.products;
      this.id = cart.id;
      let i = 0;
      this.maxQuantity = [];
      this.products.forEach((product: Product) => {
        this.maxQuantity[i++] = product.quantity;
        product.quantity = 1;
      });
      this.calculateTotalPrice();
    },
      err => {
        console.log(err);
      });


  }

  calculateTotalPrice(): void {
    this.sum = 0;
    this.products.forEach(p => {
      this.sum += p.quantity * p.price;
    })
  }

  showSuccess(successSource: string): void {
    let successString = '';
    if (successSource === 'delete') {
      successString = 'Successfully removed product!';
    }
    else if (successSource === 'checkout') {
      successString = 'Thank you for your purchase!';
    }
    this.toastrService.success(successString);
  }

  showFailure(errorSource: string): void {
    let errorString = '';
    if (errorSource === 'delete') {
      errorString = 'Error removing product from cart.';
    }
    else if (errorSource === 'checkout') {
      errorSource = 'Error creating order.';
    }
    this.toastrService.error(errorSource);
  }

  goToHomePage(): void {
    this.router.navigate(['/home']);
  }

  checkoutCart(): void {
    const newProducts = [...this.products];
    for (let i = 0; i < this.products.length; i++) {
      newProducts[i].quantity = this.maxQuantity[i] - this.products[i].quantity;
    }
    const jsonData = JSON.stringify(newProducts);
    console.log(jsonData);
    this.cartService.orderProductsInCart(this.id, jsonData).subscribe(res => {
      this.showSuccess('checkout');
      this.goToHomePage();
    },
      err => {
        this.showFailure('checkout');
      });
  }

  removeProductFromCart(productId: number): void {
    this.cartService.removeProductFromCart(this.id, productId).subscribe(data => {
      console.log(data);
      this.showSuccess('delete');
      this.ngOnInit();
    },
      err => {
        console.error(err);
        this.showFailure('delete');
      });
  }

}
