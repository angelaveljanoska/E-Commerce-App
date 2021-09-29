import { Component, OnInit } from '@angular/core';
import Product from '../_models/product';
import { ProductService } from '../_services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id!: number;
  product!: Product;
  submitted: boolean = false;
  imageError!: string;
  isImageSaved!: boolean;
  cardImageBase64!: string;

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) { }

  ngOnInit(): void {
    this.product = new Product();
    this.id = this.route.snapshot.params.id;

    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data);
        this.product = data;
      }, error => console.log(error));
  }

  updateProduct() {
    this.productService.updateProduct(this.id, this.product)
      .subscribe(data => {
        console.log(data);
        this.goToHomePage();
      }, error => console.log(error));
  }

  onSubmit() {
    this.cardImageBase64 ? this.product.photo = this.cardImageBase64 : null;
    this.updateProduct();
    this.submitted = true;
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
          this.isImageSaved = true;
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
    return 0;
  }

}
