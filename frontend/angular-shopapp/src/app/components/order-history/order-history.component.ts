import { Component, OnInit } from '@angular/core';
import {OrderHistory} from "../../common/order-history";
import {OrderHistoryService} from "../../services/order-history.service";

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  orderHistory!: OrderHistory[];
  storage: Storage = sessionStorage;
  constructor(private orderHistoryService: OrderHistoryService) { }

  ngOnInit(): void {
    this.getOrderHistory();
  }

  getOrderHistory(){
    const userEmail = JSON.parse(<string>this.storage.getItem("userEmail"));
    this.orderHistoryService.getHistoryOfAllOrdersByUserEmail(userEmail).subscribe(data =>{
      // @ts-ignore
      this.orderHistory = data;
    });
  }

}
