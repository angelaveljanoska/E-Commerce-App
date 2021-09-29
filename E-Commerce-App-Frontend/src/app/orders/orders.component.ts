import { Component, OnInit } from '@angular/core';
import Order from '../_models/order';
import Product from '../_models/product';
import ProductOrder from '../_models/productOrder';
import { ProductOrderService } from './../_services/product-order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];
  productOrders: ProductOrder[] = [];

  constructor(private productOrderService: ProductOrderService) { }

  ngOnInit(): void {
    this.productOrderService.getProductOrders().subscribe(productOrders => {
      this.productOrders = productOrders;
      this.orders.push(this.productOrders[0].order);
      for (let i = 1; i < this.productOrders.length; i++) {
        if (this.productOrders[i].order.id !== this.productOrders[i - 1].order.id) {
          const newOrder: Order = {
            ...this.productOrders[i].order,
            products: [],
          };
          this.orders.push(newOrder);
        }
      }
      this.orders.forEach(order => {
        order.products = [];
      });
      console.log(this.orders);
      this.productOrders.forEach(productOrder => {
        this.orders.forEach(order => {
          if (productOrder.order.id === order.id) {
            order.products.push({
              product: productOrder.product,
              quantity: productOrder.quantity,
            });
          }
        });
      });
    }, err => {
      console.error(err);
    });
  }
}
