package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.*;
import io.github.mac9p.shopapp.Repositories.AddressRepository;
import io.github.mac9p.shopapp.Repositories.CustomerRepository;
import io.github.mac9p.shopapp.Repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CheckoutService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CheckoutService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
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

        //check if user is already in db
        Customer purchaseCustomer = purchase.getCustomer();
        Customer customerFromDatabase;
        if (!customerRepository.existsByEmail(purchaseCustomer.getEmail())) {
            purchaseCustomer.getOrderSet().add(order);
            customerRepository.save(purchaseCustomer);
        }else {
            customerFromDatabase = customerRepository.findCustomerByEmail(purchaseCustomer.getEmail());
            customerFromDatabase.getOrderSet().add(order);
        }
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
