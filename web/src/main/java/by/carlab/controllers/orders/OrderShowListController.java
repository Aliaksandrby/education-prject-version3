package by.carlab.controllers.orders;

import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderShowListController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/showOrderList.html")
    public String showOrderList(Model model) {
        model.addAttribute("orderList",orderService.showOrderList());
        return "showOrderList";
    }
}
