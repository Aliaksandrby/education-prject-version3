package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarDeleteController {

    @Autowired
    private CarService carService;

    @Secured("ADMIN")
    @GetMapping("/admin/delete/car/{id}.html")
    public String deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/";
    }

    @Secured("ADMIN")
    @GetMapping("/rest/admin/delete/car/{id}.html")
    @ResponseBody
    public void deleteRestCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
    }
}
