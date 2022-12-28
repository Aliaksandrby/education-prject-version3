package by.carlab.controllers.orders;

import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class OrderCompleteController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/complete/car/{id}.html")
    public String cancelOrder(Model model, @PathVariable("id") int id, Principal principal) {
        model.addAttribute("car",orderService.completeOrder(principal.getName(),id));
        return "showCar";
    }
}