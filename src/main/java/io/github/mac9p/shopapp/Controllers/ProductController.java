package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
}
