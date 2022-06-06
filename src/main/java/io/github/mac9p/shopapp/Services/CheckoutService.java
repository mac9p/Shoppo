package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.*;
import io.github.mac9p.shopapp.Repositories.CustomerRepository;
import io.github.mac9p.shopapp.Repositories.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
public class CheckoutService {

    private final CustomerRepository customerRepository;

    public CheckoutService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //get order info from dto
        Order order = purchase.getOrder();
        //generate tracking number
        String orderPostNumber = generateOrderPostNumber(purchase.getShippingAddress(), order);
        order.setOrderPostNumber(orderPostNumber);


        //add orderItems to order
        purchase.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        order.setOrderItems(purchase.getOrderItems());


        //add billing and shipping Address to order

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //check if user is already in db
        Customer customer = purchase.getCustomer();
        if (!customerRepository.existsByEmail(customer.getEmail())) {
            order.setCustomer(customer);
            customer.getOrderSet().add(order);

            customerRepository.save(customer);
        } else {
            customer = customerRepository.findCustomerByEmail(customer.getEmail());
            order.setCustomer(customer);
            customer.getOrderSet().add(order);
        }

        //return a response
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setOrderPostNumber(orderPostNumber);
        return purchaseResponse;

    }

    private String generateOrderPostNumber(Address shippingAddress, Order order) {
        String uniqueId = UUID.randomUUID().toString();
        log.debug("---------------" + uniqueId);
        return shippingAddress.getCountry() + uniqueId;
    }

}
