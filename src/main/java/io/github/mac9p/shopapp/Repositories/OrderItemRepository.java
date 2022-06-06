package io.github.mac9p.shopapp.Repositories;

import io.github.mac9p.shopapp.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public Set<OrderItem> findAllByOrder_OrderPostNumber(String postNumber);

}
