import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';
import {ProductCategory} from "../common/product-category";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = "http://localhost:8080/products"
  private productCategoryUrl = "http://localhost:8080/productCategories"

  constructor(private httpClient: HttpClient) { }

  //getAllProducts(): Observable<Product[]> {
    //return this.httpClient.get<GetResponse>(this.productUrl).pipe(
      //map(response => response.products)
    //);
  //}
  getAllProducts(currentCategoryId: number): Observable<Product[]> {

    const searchUrl = `${this.productUrl}/byCategoryId?id=${currentCategoryId}`;

    return this.httpClient.get<Product[]>(searchUrl);
  }
  getProductsByKeyword(currentKeyword: String): Observable<Product[]>{
    const searchUrl = `${this.productUrl}/byKeyword?keyword=${currentKeyword}`;
    return this.httpClient.get<Product[]>(searchUrl)
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(this.productCategoryUrl)

  }
}

interface GetResponse{
    products: Product[];
}
