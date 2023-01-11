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
public class OrderCreateController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/create/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String createOrder(Model model, @PathVariable("id") int id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if(user.getIsPayment()==1) {
            model.addAttribute("pay","Please pay previous the car");
            return "payPreviousCar";
        }
        model.addAttribute("car",orderService.createOrder(principal.getName(),id));
        return "showCar";
    }
}
