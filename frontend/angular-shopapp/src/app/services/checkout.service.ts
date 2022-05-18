import { Injectable } from '@angular/core';
import {Purchase} from "../common/purchase";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  postOrder(purchase : Purchase): Observable<Purchase>{
      const url = 'http://localhost:8080/checkout/purchase';
      return this.httpClient.post<Purchase>(url,purchase);
  }

  constructor(private httpClient: HttpClient) { }
}
