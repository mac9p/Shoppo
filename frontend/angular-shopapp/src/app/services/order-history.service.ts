import { Injectable } from '@angular/core';
import {OrderHistory} from "../common/order-history";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {
  baseUrl = 'http://localhost:8080/search';

  constructor(private httpClient: HttpClient) { }


  getHistoryOfAllOrdersByUserEmail(email: string): Observable<OrderHistory> {
    const searchUrl = this.baseUrl+'/email/?email='+email;
    return this.httpClient.get<OrderHistory>(searchUrl);
  }
}
