package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
