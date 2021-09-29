import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import cart from '../_models/cart';
import Product from '../_models/product';

const API_URL = 'http://localhost:9090/cart';
@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCartForUser(userId: number): Observable<any> {
    return this.http.get(`${API_URL}/${userId}`);
  }

  // tslint:disable-next-line: no-shadowed-variable
  addCartForUser(cart: object, userId: number): Observable<any> {
    return this.http.post(`${API_URL}/${userId}`, cart);
  }

  getProductsInCart(cartId: number): Observable<any> {
    return this.http.get(`${API_URL}/${cartId}/products`);
  }

  removeProductFromCart(cartId: number, productId: number): Observable<any> {
    return this.http.delete(`${API_URL}/${cartId}/${productId}`);
  }

  orderProductsInCart(cartId: number, products: string): Observable<any> {
    return this.http.post(`http://localhost:9090/order/submit/${cartId}`, products, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

}
