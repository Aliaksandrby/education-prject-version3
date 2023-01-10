package by.carlab.controllers.orders;

import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderDeleteController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/delete/order/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin/showOrderList.html";
    }
}
