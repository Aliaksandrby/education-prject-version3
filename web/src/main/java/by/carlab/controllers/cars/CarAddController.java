package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/add/car.html")
    public String addCar() {
        return "formForNewCar";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add/car.html")
    public String createCar(Car car, Model model, @RequestParam("images") MultipartFile[] images) throws IOException {
        model.addAttribute("car",carService.addCar(car, images));
        return "showCar";
    }
}
