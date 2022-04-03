package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.ProductCategory;
import io.github.mac9p.shopapp.Repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productRepository) {
        this.productCategoryRepository = productRepository;
    }

    public List<ProductCategory> getAllCategories(){
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(Long id){
        return productCategoryRepository.findProductCategoryById(id);
    }
}
