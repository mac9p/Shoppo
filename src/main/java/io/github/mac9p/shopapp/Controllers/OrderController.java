package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    private List<Order> getAllOrders(){
        return orderService.findALlOrders();
    }

    @GetMapping("/search/email")
    private Set<Order> getOrdersByCustomerEmail(@RequestParam String email){
        return orderService.getOrdersByCustomerEmail(email);
    }
}
