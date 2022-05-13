import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CartService} from "../../services/cart.service";
import {ShoppoFormService} from "../../services/shoppo-form.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup!: FormGroup;

  totalPrice!: number;
  totalQuantity!: number;
  creditCardMonths: number[] = [];
  creditCardYears: number[] = [];

  constructor(private formBuilder: FormBuilder, private cartService: CartService, private formService: ShoppoFormService) {
  }

  ngOnInit(): void {

    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({

        firstName: new FormControl('', [Validators.required, Validators.minLength(3)]),

        lastName: new FormControl('', [Validators.required, Validators.minLength(3)]),

        email: new FormControl('', [Validators.required,
          Validators.pattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")]),

        country: new FormControl('', [Validators.required]),

        phoneNumber: new FormControl('', [Validators.required,
          Validators.pattern("/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/")]),
      }),
      shipping: this.formBuilder.group({
        country: new FormControl('', [Validators.required]),
        city: new FormControl('',[Validators.required,Validators.minLength(3)]),
        street: new FormControl('',[Validators.required,Validators.minLength(3)]),
        number: new FormControl('',[Validators.required,Validators.minLength(3)]),
        postalCode:  new FormControl('',Validators.required),
      }),
      billing: this.formBuilder.group({
        country: new FormControl('', [Validators.required]),
        city: new FormControl('',[Validators.required,Validators.minLength(3)]),
        street: new FormControl('',[Validators.required,Validators.minLength(3)]),
        number: new FormControl('',[Validators.required,Validators.minLength(3)]),
        postalCode:  new FormControl('',Validators.required),
        expirationMonth: new FormControl(0,Validators.required),
        expirationYear: new FormControl(0,Validators.required),
      })
    });
    this.getCartDetails();
    this.getCreditCardMonths();
    this.getCreditCardYears();
  }

  get firstName(){
    return this.checkoutFormGroup.get('customer.firstName')!;
  }
  get lastName(){
    return this.checkoutFormGroup.get('customer.lastName')!;
  }
  get email(){
    return this.checkoutFormGroup.get('customer.email')!;
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
    this.formService.getCreditCardYears().subscribe(data => this.creditCardYears = data);
  }

  submit() {
    console.log(this.checkoutFormGroup.get("customer")?.value);

    if (this.checkoutFormGroup.invalid){
      this.checkoutFormGroup.markAllAsTouched();
    }
  }

}

