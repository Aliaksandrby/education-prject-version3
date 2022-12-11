package by.carlab.controllers;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarDeleteController {
    @Autowired
    private CarService carService;
    @GetMapping("/delete/car/{id}.html")
    public String deleteCar(Model model, @PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
}
