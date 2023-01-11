package by.carlab.controllers.orders;

import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ShowOwnOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/showOwnOrder.html")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String showOwnOrder(Model model, Principal principal) {
        model.addAttribute("order",orderService.findOrderByUsername(principal.getName()));
        return "showOwnOrder";
    }
}
