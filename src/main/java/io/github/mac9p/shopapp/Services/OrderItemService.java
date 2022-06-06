package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.OrderItem;
import io.github.mac9p.shopapp.Repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public Set<OrderItem> findAllItemsByPostNumber(@NotBlank String postNumber){
        return orderItemRepository.findAllByOrder_OrderPostNumber(postNumber);
    }
}
