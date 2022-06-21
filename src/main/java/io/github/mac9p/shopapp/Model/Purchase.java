package io.github.mac9p.shopapp.Model;

import io.github.mac9p.shopapp.Model.Address;
import io.github.mac9p.shopapp.Model.Customer;
import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private List<OrderItem> orderItems = new LinkedList<>();
}
