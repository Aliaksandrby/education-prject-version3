package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CarShowController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/car/{id}.html")
    public String viewCar(Model model, @PathVariable("id") int id, Principal principal) {
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        model.addAttribute("car",carService.getCar(id));
        return "showCar";
    }
}
