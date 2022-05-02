package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    static private Product product1;
    static private Product product2;
    static private ProductCategory category1;

    @BeforeAll
    static void setUp() {
        category1 = new ProductCategory();
        product1 = new Product(
                "exSku1",
                "exName1",
                "exDesc1",
                30.0,
                "exUrl1",
                true,
                15,
                category1
        );
        product2 = new Product(
                "exSku2",
                "exName2",
                "exDesc2",
                30.0,
                "exUrl2",
                true,
                15,
                category1
        );
    }

    @Test
    void itShouldfindProductById() {
        //given

        Product savedProduct = underTest.save(product1);

        //when
        Product foundProduct = underTest.findProductById(savedProduct.getId());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + foundProduct);
        //then
        assertThat(foundProduct).isEqualTo(savedProduct);
    }

    @Test
    void itShouldfindProductsByProductCategoryId() {
        //given

        Product saved1 = underTest.save(product1);
        Product saved2 = underTest.save(product2);

        //when

        List<Product> foundProducts = underTest.findProductsByProductCategoryId(1L);

        //then

        assertThat(Arrays.asList(saved1, saved2)).isEqualTo(foundProducts);

    }

    @Test
    void itShouldFindProductsByNameContaining() {
        //given
        Product saved1 = underTest.save(product1);
        Product saved2 = underTest.save(product2);
        //when

        List<Product> foundProducts = underTest.findProductsByNameContaining("1");

        //then

        assertThat(Collections.singletonList(saved1)).isEqualTo(foundProducts);
    }
}