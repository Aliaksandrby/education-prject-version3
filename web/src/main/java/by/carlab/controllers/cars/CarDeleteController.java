package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarDeleteController {

    @Autowired
    private CarService carService;

    //localhost:8080/rent/admin/delete/car/i.html

    @GetMapping("/admin/delete/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
}
