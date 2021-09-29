import { Component, Input, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import Product from '../_models/product';
import { ProductOrderService } from '../_services/product-order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  @Input() id!: any;
  @Input() status!: string;
  @Input() products: {
    product: Product,
    quantity: number,
  }[] = [];
  @Input() userEmail = '';
  @Input() date: Date = new Date();

  constructor(private productOrderService: ProductOrderService, private toastrService: ToastrService) { }

  ngOnInit(): void {
  }

  showSuccess(successSource: string): void {
    let successString = '';
    if (successSource === 'approve') {
      successString = 'Successfully approved order!';
    }
    this.toastrService.success(successString);
  }

  showWarning(warningSource: string): void {
    let warningString = '';
    if (warningSource === 'decline') {
      warningString = 'Order has been declined!';
    }
    this.toastrService.warning(warningString);
  }

  updateOrder(status: boolean): void {
    this.productOrderService.setOrderStatus(this.id, status).subscribe(data => {
      console.log(data);
      status ? this.showSuccess('approve') : this.showWarning('decline');
      setTimeout(function(){
      window.location.reload();
    },1000);
      //window.location.reload();
    }, err => {
      console.error(err);
    });
  }

}
