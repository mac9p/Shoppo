package io.github.mac9p.shopapp.dto;

import io.github.mac9p.shopapp.Model.Address;
import io.github.mac9p.shopapp.Model.Customer;
import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Model.OrderItem;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItemSet = new HashSet<>();
}
