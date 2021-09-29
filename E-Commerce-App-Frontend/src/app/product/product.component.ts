import { AddProductComponent } from './../add-product/add-product.component';
import { Component, OnInit, Input } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { ProductService } from '../_services/product.service';
import Product from '../_models/product';
import { Observable } from 'rxjs';
import { CartService } from '../_services/cart.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  animations: [
    trigger(
      'inOutAnimation',
      [
        transition(
          ':enter',
          [
            style({ opacity: 0, transform: 'scaleY(0)' }),
            animate('0.3s ease-out',
              style({ opacity: 1, transform: 'scaleY(1)' }))
          ]
        ),
        transition(
          ':leave',
          [
            style({ opacity: 1 }),
            animate('0.3s ease-in',
              style({ opacity: 0 }))
          ]
        )
      ]
    )
  ]
})
export class ProductComponent implements OnInit {
  @Input() id!: any;
  @Input() name = "";
  @Input() description = "";
  @Input() quantity!: number;
  @Input() price!: number;
  @Input() photo!: string;

  private roles: string[] = [];
  products!: Product[];
  isLoggedIn = false;
  showAdminButtons = false;
  showCustomerButtons = false;
  showDeleteModal = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router, private productService: ProductService, private cartService: CartService, private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminButtons = this.roles.includes('ROLE_ADMIN');
      this.showCustomerButtons = this.roles.includes('ROLE_CUSTOMER');
    }
  }

  showSuccess() {
    this.toastrService.success('Successfully added product to cart.');
  }

  showFailure(errorSource: string) {
    let errorString: string = "";
    if (errorSource === 'cart') {
      errorString = 'Product already in cart. Change the quantity to order more.';
    }
    else if (errorSource === 'delete') {
      errorString = 'Error while deleting product.';
    }
    this.toastrService.error(errorString);
  }

  deleteProduct() {
    this.productService.deleteProduct(this.id)
      .subscribe(data => {
        console.log(data);
        window.location.reload();
      }, error => {
        this.showFailure('delete');
      }
      );

  }

  updateProduct() {
    this.router.navigate(['update', this.id]);
  }

  addProductToCart() {
    const user = this.tokenStorageService.getUser();
    let userCart = {
      id: 0,
      userId: 0,
    };
    this.cartService.getCartForUser(user.id)
      .subscribe(data => {
        userCart = data;
        this.cartService.getProductsInCart(userCart.id).subscribe(products => {
          let productsArray: Product[];
          productsArray = products;
          if (!productsArray.some((p: Product) => p.id === this.id)) {
            this.productService.addProductToCart(this.id, userCart.id).subscribe(data1 => {
              console.log(data1);
              this.showSuccess();
              this.router.navigate(['/cart']);
            }, error => {
              this.showFailure('cart');
              this.router.navigate(['/cart']);
            });
          }
          else {
            this.showFailure('cart');
          }
        }, errorP => {
          console.error(errorP);
        });
      }, err => {
        console.error(err);
      });
  }

}
