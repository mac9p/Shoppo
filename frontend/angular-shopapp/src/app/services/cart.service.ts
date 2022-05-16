import {Injectable} from '@angular/core';
import {CartItem} from "../common/cart-item";
import {BehaviorSubject, Observable, ReplaySubject, Subject} from "rxjs";
import {newArray} from "@angular/compiler/src/util";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);


  constructor() {
  }


  addToCart(addedCartItem: CartItem) {
    let isAlreadyInCart: boolean = false;
    let existingCartItem: CartItem = undefined!;

    if (this.cartItems.length > 0) {

      existingCartItem = this.cartItems.find(item => item.id == addedCartItem.id)!;

      isAlreadyInCart = (existingCartItem != undefined)

      if (isAlreadyInCart) {
        existingCartItem.quantity++;
      } else {
        this.cartItems.push(addedCartItem);
      }

      this.countTotalCart();
    }else{
      this.cartItems.push(addedCartItem);
      this.countTotalCart();
    }
  }
  /*getProductsInCart(): Observable<CartItem>{

  }*/

  countTotalCart() {
    let totalPrice: number = 0;
    let totalQuantity: number = 0;
    this.cartItems.forEach(item => totalPrice += item.unitPrice * item.quantity);
    this.cartItems.forEach(item => totalQuantity += item.quantity);

    this.totalPrice.next(totalPrice);
    this.totalQuantity.next(totalQuantity);
  }

  removeFromCart(itemToRemove: CartItem){
    let existingCartItem: CartItem = undefined!;

    if (this.cartItems.length > 0) {

      existingCartItem = this.cartItems.find(item => item.id == itemToRemove.id)!;

      if (existingCartItem.quantity>1)
        existingCartItem.quantity--;
      else {
        this.cartItems.forEach( (item, index) => {
          if(item == itemToRemove) this.cartItems.splice(index,1);
      });
    }
    }
    this.countTotalCart();
  }
}
