package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarDeleteController {

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    //localhost:8080/rent/admin/delete/car/i.html
    @GetMapping("/admin/delete/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteCar(Model model, @PathVariable("id") int id) {

        if(orderService.isCarInOrder(id)) {
            return "carInOrder";
        }

        carService.deleteCar(id);
        return "redirect:/";
    }
}
