package by.carlab.controllers;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CarAddController {
    @Autowired
    private CarService carService;
    @GetMapping("/add/car.html")
    public String addCar() {
        return "formForNewCar";
    }
    @PostMapping("/add/car.html")
    public String createCar(Car car, Model model, @RequestParam("images") MultipartFile[] images) throws IOException {
        System.out.println("create car");
        model.addAttribute("car",carService.addCar(car, images));
        return "showCar";
    }
}
