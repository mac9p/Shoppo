package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Autowired
    private ProductCategoryRepository productCatRepo;



    @Test
    void itShouldfindProductById() {
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
        Product savedProduct = underTest.save(product);

        //when
        Product foundProduct = underTest.findProductById(1L);

        //then
        assertThat(foundProduct).isEqualTo(savedProduct);
    }

    @Test
    void itShouldfindProductsByProductCategoryId() {
        //given
        ProductCategory category = productCatRepo.save(new ProductCategory());
        Product product1 = new Product(
                "exSku1",
                "exName1",
                "exDesc1",
                30.0,
                "exUrl1",
                true,
                15,
                category
        );
        Product product2 = new Product(
                "exSku2",
                "exName2",
                "exDesc2",
                30.0,
                "exUrl2",
                true,
                15,
                category
        );
        underTest.save(product1);
        underTest.save(product2);

        //when

        List<Product> foundProducts = underTest.findProductsByProductCategoryId(1L);

        //then

        assertThat(Arrays.asList(product1,product2)).isEqualTo(foundProducts);

    }
}