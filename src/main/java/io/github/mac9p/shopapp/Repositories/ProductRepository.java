package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Product;
import io.github.mac9p.shopapp.Model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product findProductById(Long id);

    public List<Product> findProductsByProductCategoryId(Long id);
}
