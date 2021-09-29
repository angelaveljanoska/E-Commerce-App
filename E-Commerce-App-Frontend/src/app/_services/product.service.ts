import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:9090/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get(API_URL);
  }

  getProduct(id: number): Observable<any> {
    return this.http.get(`${API_URL}/${id}`);
  }

  addProduct(product: object): Observable<any> {
    return this.http.post(API_URL, product);
  }

  updateProduct(id: number, value: any): Observable<any> {
    return this.http.put(`${API_URL}/${id}`, value);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${API_URL}/${id}`, { responseType: 'text' });
  }

  addProductToCart(id: number, cartId: number): Observable<any> {
    return this.http.post(`${API_URL}/${cartId}/${id}`, null);
  }
}

