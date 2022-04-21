import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = "http://localhost:8080/products"

  constructor(private httpClient: HttpClient) { }

  //getAllProducts(): Observable<Product[]> {
    //return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
      //map(response => response.products)
    //);
  //}
  getAllProducts(currentCategoryId: number): Observable<Product[]> {

    const searchUrl = `${this.baseUrl}/byCategoryId?id=${currentCategoryId}`;

    return this.httpClient.get<Product[]>(searchUrl)
  }
}

interface GetResponse{
    products: Product[];
}
