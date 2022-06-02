package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductCategoryRepositoryTest {


    @Autowired
    private ProductCategoryRepository underTest;


    @Test
    void findProductCategoryById() {

        //given
        ProductCategory savedCategory = underTest.save(new ProductCategory());
        //when
        ProductCategory foundProductCategory = underTest.findProductCategoryById(1L);
        //then
        assertThat(savedCategory).isEqualTo(foundProductCategory);

    }
}