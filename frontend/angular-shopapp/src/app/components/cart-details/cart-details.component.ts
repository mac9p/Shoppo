import { Component, OnInit } from '@angular/core';
import {Product} from "../../common/product";
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../common/cart-item";
import {Subject} from "rxjs";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  public cartItems: CartItem[] = [];
  totalPrice: number = 0.00;
  totalQuantity: number = 0.00;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getProductsInCart();
    this.getCartDetails();
  }


  getProductsInCart() {
    this.cartItems = this.cartService.cartItems;
  }

  getCartDetails() {
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    this.cartService.countTotalCart();
    console.log("HERE"+this.totalPrice)
  }
}
