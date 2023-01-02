package by.carlab.controllers.orders;

import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderShowController {

    @Autowired
    private OrderService orderService;

    @Secured("ADMIN")
    @GetMapping("/admin/order/{id}.html")
    public String showOrder(Model model, @PathVariable("id") int id) {
        model.addAttribute("order",orderService.getOrder(id));
        return "showOrder";
    }
}
