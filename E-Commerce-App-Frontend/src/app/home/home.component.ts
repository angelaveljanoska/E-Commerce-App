import { Component, OnInit } from '@angular/core';
import { ProductService } from '../_services/product.service';
import Product from '../_models/product';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products!: Product[];
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminButtons = false;
  showCustomerButtons = false;

  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    /*this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );*/
    this.productService.getProducts().subscribe(
      data => {
        this.products = data;
        console.log(this.products);
      },
      err => {
        console.error(err);
      }
    );

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminButtons = this.roles.includes('ROLE_ADMIN');
      this.showCustomerButtons = this.roles.includes('ROLE_CUSTOMER');
    }
  }

}
