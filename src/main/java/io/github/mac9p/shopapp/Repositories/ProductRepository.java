package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product findProductById(Long id);
}
