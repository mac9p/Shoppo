package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(@NotNull Product product){
        return productRepository.save(product);
    }

    public Product findProductById(Long id){
        return productRepository.findProductById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
