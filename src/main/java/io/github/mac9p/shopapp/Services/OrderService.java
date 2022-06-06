package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findALlOrders(){
        return orderRepository.findAll();
    }

    public Set<Order> getOrdersByCustomerEmail(@NotBlank String email){
        return orderRepository.findOrderByCustomer_Email(email);
    }

    public Order findOrderByOrderPostNumber(@NotBlank String postNumber){
        return orderRepository.findOrderByOrderPostNumber(postNumber);
    }
}
