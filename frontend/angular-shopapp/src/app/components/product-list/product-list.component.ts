import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',

  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  products!: Product[];
  currentCategoryName!: string;
  currentCategoryId!: number;
  constructor(private productService: ProductService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getAllProducts();
    this.route.paramMap.subscribe(() => {
      this.getAllProducts();
    })
  }

  getAllProducts(){

    if(this.route.snapshot.paramMap.has('id')){
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;

      this.currentCategoryName = this.route.snapshot.paramMap.get('categoryName')!;
    }else{
      this.currentCategoryId = 1;

      this.currentCategoryName = "BOOKS";
    }

    this.productService.getAllProducts(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    );
  }
}
