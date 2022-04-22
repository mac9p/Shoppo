package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProductService(productRepository);
    }

    @Test
    void getAllCategories() {
        //when
        underTest.getAllProducts();
        //then
        verify(productRepository).findAll();

    }

    @Test
    void getProductCategoryById() {
        //when
        underTest.findProductById(1l);
        //then
        verify(productRepository).findProductById(1l);
    }
}