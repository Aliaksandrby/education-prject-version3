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
public class OrderAnyCompleteController { // todo
    @Autowired
    private OrderService orderService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/order/complete/{id}.html")
    public String completeAnyOrder(Model model, Principal principal, @PathVariable("id") int id) {
        model.addAttribute("order",orderService.completeAnyOrder(id));
        return "showOrder";
    }
}
