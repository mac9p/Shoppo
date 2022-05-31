package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Set<Order> findOrderByCustomer_Email(String email);
}
