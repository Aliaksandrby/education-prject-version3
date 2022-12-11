package by.carlab.controllers;

import by.carlab.cars.CarShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarShowController {
    @Autowired
    private CarShowService carShowService;
    @GetMapping("/car/{id}.html")
    public String viewCar(Model model, @PathVariable("id") int id) {
        model.addAttribute("car",carShowService.getCar(id));
        return "showCar";
    }
}
