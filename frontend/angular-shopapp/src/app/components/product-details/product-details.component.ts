import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../common/product";

@Component({
  selector: 'app-product-details-component',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {



  public product!: Product;
  constructor(private productService: ProductService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getProductDetails();
  }

  private getProductDetails(){
    const productId: number = +this.route.snapshot.paramMap.get('id')!;

    this.productService.getProduct(productId).subscribe(
      data =>{
        this.product = data;
      }
    )

  }

}
