package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.ProductCategory;
import io.github.mac9p.shopapp.Services.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategory> getAllCategories(){
        return productCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ProductCategory getCategoryById(@PathVariable Long id){
        return productCategoryService.getProductCategoryById(id);
    }
}
