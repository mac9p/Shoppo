package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Services.CheckoutService;
import io.github.mac9p.shopapp.Model.Purchase;
import io.github.mac9p.shopapp.Model.PurchaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/checkout")
public class CheckoutController {


    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        return checkoutService.placeOrder(purchase);
    }
}
