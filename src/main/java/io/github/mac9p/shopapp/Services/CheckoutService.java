package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Address;
import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Repositories.CustomerRepository;
import io.github.mac9p.shopapp.Repositories.OrderRepository;
import io.github.mac9p.shopapp.dto.Purchase;
import io.github.mac9p.shopapp.dto.PurchaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public CheckoutService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase){
        //get order info from dto
        Order order = purchase.getOrder();
        //generate tracking number
        String orderPostNumber = generateOrderPostNumber(purchase.getShippingAddress(),order);
        order.setOrderPostNumber(orderPostNumber);

        //add orderItems to order
        purchase.getOrderItemSet().forEach(orderItem -> order.getOrderItems().add(orderItem));

        //add billing and shipping Address to order

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //add order to customer

        purchase.getCustomer().getOrderSet().add(order);
        //save to the database
        customerRepository.save(purchase.getCustomer());
        orderRepository.save(order);

        //return a response
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setOrderPostNumber(orderPostNumber);
        return purchaseResponse;

    }

    private String generateOrderPostNumber(Address shippingAddress,Order order) {
        String uniqueId = UUID.randomUUID().toString();
        log.debug("---------------"+uniqueId);
        return shippingAddress.getCountry()+uniqueId;
    }

}
