import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Product} from 'src/app/common/product';
import {ProductService} from 'src/app/services/product.service';
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../common/cart-item";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',

  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  products!: Product[];
  currentCategoryName!: string;
  currentCategoryId!: number;
  searchMode!: boolean;
  currentKeyword!: String;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getAllProducts();
    this.route.paramMap.subscribe(() => {
      this.getAllProducts();
    })
  }

  getAllProducts() {

    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if (this.searchMode){
      this.handleSearchProducts();
    }
    else {

      this.handleGetProducts();
    }
  }

  handleGetProducts() {
    if (this.route.snapshot.paramMap.has('id')) {
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;

      this.currentCategoryName = this.route.snapshot.paramMap.get('categoryName')!;
    } else {
      this.currentCategoryId = 1;

      this.currentCategoryName = "BOOKS";
    }

    this.productService.getAllProducts(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    );
  }

  private handleSearchProducts() {
    this.currentKeyword = this.route.snapshot.paramMap.get('keyword')!;
    /*if (this.currentKeyword == ""){
      return;
    }*/
    this.productService.getProductsByKeyword(this.currentKeyword).subscribe(
      data =>{
        this.products = data;
      }
    )
  }
  addToCart(product: Product){
    console.log(`Added to cart: ${product.name}, ${product.unitPrice}`);

    let cartItem = new CartItem(product);

    this.cartService.addToCart(cartItem);
  }
}
