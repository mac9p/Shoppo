package io.github.mac9p.shopapp.Services;

import io.github.mac9p.shopapp.Model.Address;
import io.github.mac9p.shopapp.Model.Customer;
import io.github.mac9p.shopapp.Model.Order;
import io.github.mac9p.shopapp.Repositories.CustomerRepository;
import io.github.mac9p.shopapp.Repositories.OrderRepository;
import io.github.mac9p.shopapp.Model.Purchase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CheckoutService checkoutService;

    @Test
    void placeOrder() {
        //given
        Purchase purchase = new Purchase(new Customer(),new Address(),new Address(),new Order(),new HashSet<>());
        //when


        checkoutService.placeOrder(purchase);
        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
    }
}