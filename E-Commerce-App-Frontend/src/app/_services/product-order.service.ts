import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import ProductOrder from '../_models/productOrder';

const API_URL = 'http://localhost:9090/order';
@Injectable({
  providedIn: 'root'
})
export class ProductOrderService {

  constructor(private http: HttpClient) { }

  getProductOrders(): Observable<any> {
    return this.http.get(`${API_URL}/productorders`);
  }

  setOrderStatus(orderId: number, status: boolean): Observable<any> {
    return this.http.put(`${API_URL}/${orderId}`, status);
  }
}
