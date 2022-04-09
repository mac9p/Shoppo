package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Services.ProductService;
import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200")
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
