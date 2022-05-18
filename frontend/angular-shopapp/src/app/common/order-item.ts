import {CartItem} from "./cart-item";

export class OrderItem {
  private imageUrl!: string;
  private quantity!: number;
  private unitPrice!: number;
  private productId!: number;

  constructor(cartItem: CartItem) {
    this.imageUrl = cartItem.imageUrl;
    this.quantity = cartItem.quantity;
    this.unitPrice = cartItem.unitPrice;
    this.productId = cartItem.id
  }
}
