import { Component, OnInit } from '@angular/core';
import Product from '../_models/product';
import { ProductService } from '../_services/product.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product = new Product();
  isSubmitted = false;
  imageError!: string;
  isImageSaved!: boolean;
  cardImageBase64!: string;

  constructor(private productService: ProductService, private router: Router, private toastrService: ToastrService) { }

  ngOnInit(): void {
  }

  newProduct(): void {
    this.isSubmitted = false;
    this.product = new Product();
  }

  showSuccess() {
    this.toastrService.success('You added a new product!');
  }

  showFailure() {
    this.toastrService.error('Could not add a new product!');
  }

  save() {
    this.productService
      .addProduct(this.product).subscribe(data => {
        console.log(data);
        this.product = new Product();
        this.showSuccess();
        this.goToHomePage();
      },
        error => {
          this.showFailure();
          this.goToHomePage();
        });
  }

  onSubmit() {
    this.isSubmitted = true;
    this.save();
  }

  goToHomePage() {
    this.router.navigate(['/home']);
  }
  fileChangeEvent(fileInput: any) {
    this.imageError = "";
    if (fileInput.target.files && fileInput.target.files[0]) {
      // Size Filter Bytes
      const max_size = 20971520;
      const allowed_types = ['image/png', 'image/jpeg'];
      const max_height = 15200;
      const max_width = 25600;

      if (fileInput.target.files[0].size > max_size) {
        return false;
      }

      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = rs => {
          const imgBase64Path = e.target.result;
          this.cardImageBase64 = imgBase64Path;
          this.product.photo = imgBase64Path;
          this.isImageSaved = true;
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
    return 0;
  }

}
