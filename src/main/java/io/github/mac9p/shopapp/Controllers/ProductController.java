package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Services.ProductService;
import net.minidev.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/byCategoryId")
    public List<Product> findProductsByCategoryId(@RequestParam Long id){
        return productService.findProductsByCategoryId(id);
    }

    @GetMapping("/byKeyWord")
    public List<Product> findProductsByKeyword(@RequestParam String keyword){
        return productService.findProductByNameContains(keyword);
    }
}
