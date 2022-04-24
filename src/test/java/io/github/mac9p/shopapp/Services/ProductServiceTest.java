package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Model.ProductCategory;
import io.github.mac9p.shopapp.Repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        //underTest = new ProductService(productRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void canGetAllProducts() {
        //when
        underTest.getAllProducts();
        //then
        verify(productRepository).findAll();
    }

    @Test
    void canAddProduct() {
        //given
        Product product = new Product(
                "exSku",
                "exName",
                "exDesc",
                30.0,
                "exUrl",
                true,
                15,
                new ProductCategory()
        );
        //when
        underTest.addProduct(product);
        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void findProductById() {
        //when
        underTest.findProductById(1l);
        //then
        verify(productRepository,times(1)).findProductById(1l);


    }

    @Test
    void findProductsByCategoryId() {
        //when
        underTest.findProductsByCategoryId(1l);
        //then
        verify(productRepository,times(1)).findProductsByProductCategoryId(1l);

    }
}