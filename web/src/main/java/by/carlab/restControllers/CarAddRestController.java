package by.carlab.restControllers;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class CarAddRestController {

    @Autowired
    private CarService carService;

    //curl -s http://localhost:8080/rent/rest/admin/add/car.html --user admin:admin

    @PostMapping("/rest/admin/add/car.html")
    @Secured(value = {"ROLE_ADMIN"})
    public Car createCar(Car car, @RequestParam("images") MultipartFile[] images) throws IOException {
        return carService.addCar(car, images);
    }
}
