package by.carlab.controllers.orders;

import by.carlab.model.User;
import by.carlab.orders.OrderService;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String completePayment(Model model, Principal principal, @PathVariable("id") int id) {
        model.addAttribute("car",orderService.completeOrder(principal.getName(),id));
        return "showCar";
    }
}
