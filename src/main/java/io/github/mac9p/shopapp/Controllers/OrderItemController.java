package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.OrderItem;
import io.github.mac9p.shopapp.Services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/search/postNumber")
    public Set<OrderItem> findAllByPostNumber(@RequestParam String postNumber){
        return orderItemService.findAllItemsByPostNumber(postNumber);
    }
}
