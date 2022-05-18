import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CartService} from "../../services/cart.service";
import {ShoppoFormService} from "../../services/shoppo-form.service";
import {ShoppoValidators} from "../../common/validators/shoppo-validators";
import {CheckoutService} from "../../services/checkout.service";
import {Purchase} from "../../common/purchase";
import {Order} from "../../common/order";
import {OrderItem} from "../../common/order-item";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup!: FormGroup;

  totalPrice!: number;
  totalQuantity!: number;
  creditCardMonths!: number[];
  creditCardYears!: number[];

  constructor(private formBuilder: FormBuilder,private router: Router, private cartService: CartService, private formService: ShoppoFormService,private checkoutService: CheckoutService) {
  }

  ngOnInit(): void {

    this.getCartDetails();
    this.getCreditCardMonths();
    this.getCreditCardYears();
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({

        firstName: new FormControl('', [Validators.required, ShoppoValidators.notWhiteSpace, Validators.minLength(3)]),

        lastName: new FormControl('', [Validators.required, ShoppoValidators.notWhiteSpace, Validators.minLength(3)]),

        email: new FormControl('', [Validators.required,
          Validators.pattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")]),

        country: new FormControl('', [Validators.required]),

        phoneNumber: new FormControl('', [Validators.required])
      }),
      shipping: this.formBuilder.group({
        country: new FormControl('', [Validators.required]),
        city: new FormControl('', [Validators.required, Validators.minLength(3)]),
        street: new FormControl('', [Validators.required, Validators.minLength(3)]),
        number: new FormControl('', [Validators.required, ShoppoValidators.notWhiteSpace]),
        postalCode: new FormControl('', [Validators.required]),
      }),
      billing: this.formBuilder.group({
        country: new FormControl('', [Validators.required]),
        city: new FormControl('', [Validators.required, Validators.minLength(3)]),
        street: new FormControl('', [Validators.required, Validators.minLength(3)]),
        number: new FormControl('', [Validators.required, ShoppoValidators.notWhiteSpace]),
        postalCode: new FormControl('', [Validators.required]),
        cardNumber: new FormControl('', [Validators.required, ShoppoValidators.notWhiteSpace,
          Validators.minLength(13)]),
        expirationMonth: new FormControl(0, Validators.required),
        expirationYear: new FormControl(0, Validators.required),
      })
    });
  }
  get firstName() {
    return this.checkoutFormGroup.get('customer.firstName')!;
  }

  get lastName() {
    return this.checkoutFormGroup.get('customer.lastName')!;
  }

  get email() {
    return this.checkoutFormGroup.get('customer.email')!;
  }

  get phoneNumber() {
    return this.checkoutFormGroup.get('customer.phoneNumber')!;
  }

  get shippingCity() {
    return this.checkoutFormGroup.get('shipping.city')!;
  }

  get shippingStreet() {
    return this.checkoutFormGroup.get('shipping.street')!;
  }

  get shippingNumber() {
    return this.checkoutFormGroup.get('shipping.number')!;
  }

  get shippingPostalCode() {
    return this.checkoutFormGroup.get('shipping.postalCode')!;
  }

  get billingCity() {
    return this.checkoutFormGroup.get('billing.city')!;
  }

  get billingStreet() {
    return this.checkoutFormGroup.get('billing.street')!;
  }

  get billingNumber() {
    return this.checkoutFormGroup.get('billing.number')!;
  }

  get billingPostalCode() {
    return this.checkoutFormGroup.get('billing.postalCode')!;
  }

  get billingCardNumber() {
    return this.checkoutFormGroup.get('billing.cardNumber')!;
  }

  getCartDetails() {
    this.cartService.totalPrice.subscribe(data => this.totalPrice = data);
    this.cartService.totalQuantity.subscribe(data => this.totalQuantity = data);
    this.cartService.countTotalCart();
  }

  getCreditCardMonths() {
    this.formService.getCreditCardMonths().subscribe(data => this.creditCardMonths = data);
    console.log(this.creditCardMonths);
  }


  getCreditCardYears() {
    this.formService.getCreditCardYears().subscribe(data => console.log(data));
    this.formService.getCreditCardYears().subscribe(data => this.creditCardYears = data);
  }

  submit() {
    //console.log(this.checkoutFormGroup.get("customer")?.value);

    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
    }else {
      let purchase = new Purchase();
      purchase.customer = this.checkoutFormGroup.get('customer')?.value;
      purchase.orderItems = this.cartService.cartItems.map(cartItem => new OrderItem(cartItem));
      purchase.shippingAddress = this.checkoutFormGroup.get('shipping')?.value;
      purchase.billingAddress = this.checkoutFormGroup.get('billing')?.value;
      let order = new Order();
      order.totalPrice = this.totalPrice;
      order.totalQuantity = this.totalQuantity;
      purchase.order = order;
      console.log(purchase.billingAddress);
      this.checkoutService.postOrder(purchase).subscribe()

      this.resetCart();
    }

  }

  resetCart(){
    this.cartService.cartItems = [];
    this.cartService.totalQuantity.next(0);
    this.cartService.totalPrice.next(0);

    this.checkoutFormGroup.reset();

    this.router.navigateByUrl("/products");
  }

}

