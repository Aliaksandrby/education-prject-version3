package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class CarAddController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/add/car.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String addCar() {
        return "formForNewCar";
    }

    @PostMapping("/admin/add/car.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String createCar(Car car, Model model, @RequestParam("images") MultipartFile[] images, Principal principal) throws IOException {
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        model.addAttribute("car",carService.addCar(car, images));
        return "showCar";
    }
}
