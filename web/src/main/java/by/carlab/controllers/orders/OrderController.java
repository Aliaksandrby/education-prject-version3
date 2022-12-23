package by.carlab.controllers.orders;

import by.carlab.cars.CarShowService;
import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarShowService carShowService;

    @GetMapping("/order/car/{id}.html")
    public String createOrder(Model model, @PathVariable("id") int id) {
        model.addAttribute("car",carShowService.getCar(id));
        return "createOrder";
    }

    @PostMapping("/order/car/{id}.html")
    public String createOrder(@PathVariable("id") int id) {
        return "";
    }
}
