package by.carlab.controllers;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminCarDeleteController {
    @Autowired
    private CarService carService;
    @GetMapping("/admin/delete/car/{id}.html")
    public String deleteCar(Model model, @PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/1.html";
    }
}
