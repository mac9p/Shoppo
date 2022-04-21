package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Model.ProductCategory;
import io.github.mac9p.shopapp.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Product> findProductsByCategoryId(Long id){
        return productRepository.findProductsByProductCategoryId(id);
    }
}
