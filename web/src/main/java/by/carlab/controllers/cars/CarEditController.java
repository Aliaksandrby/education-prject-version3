package by.carlab.controllers.cars;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CarEditController {
    @Autowired
    private CarService carService;
    @PostMapping("/admin/edit/car/{id}.html")
    public String editCar(Car car, Model model, @PathVariable("id") int id,
                          @RequestParam("images") MultipartFile[] images) throws IOException {
        model.addAttribute("car",carService.editCar(car,id,images));
        return "showCar";
    }
}