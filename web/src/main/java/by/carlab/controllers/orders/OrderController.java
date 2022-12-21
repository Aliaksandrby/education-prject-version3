package by.carlab.controllers.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order/car/{id}.html")
    public String createOrder() {
        return "createOrder";
    }
}
