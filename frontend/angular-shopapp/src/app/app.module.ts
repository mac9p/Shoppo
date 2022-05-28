import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HttpClientModule} from '@angular/common/http';
import { ProductService } from './services/product.service';
import { Routes, RouterModule } from '@angular/router';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import {CartService} from "./services/cart.service";
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import {ReactiveFormsModule} from "@angular/forms";
import {CheckoutService} from "./services/checkout.service";
import {ShoppoFormService} from "./services/shoppo-form.service";
import { LoginComponent } from './components/login/login.component';
import {OktaAuthModule} from "@okta/okta-angular";
import { LoginStatusComponent } from './components/login-status/login-status.component';

const routes: Routes = [
  {path: 'search/:keyword', component: ProductListComponent},
  {path: 'category/:id/:categoryName',component: ProductListComponent},
  {path: 'products/:id',component: ProductDetailsComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'checkout',component: CheckoutComponent},
  {path: 'category',component: ProductListComponent},
  {path: 'products',component: ProductListComponent},
  {path: '', redirectTo: "/products", pathMatch: 'full'},
  {path: '**', redirectTo: "/products", pathMatch: 'full'},


];

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductCategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent,
    LoginComponent,
    LoginStatusComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    OktaAuthModule
  ],
  providers: [ProductService,CartService,CheckoutService,ShoppoFormService],
  bootstrap: [AppComponent]
})
export class AppModule { }
