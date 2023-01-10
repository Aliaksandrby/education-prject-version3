package by.carlab.restControllers;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarShowRestController {

    @Autowired
    private CarService carService;

    //curl localhost:8080/rent/rest/car/carId.html
    @GetMapping("/rest/car/{id}.html")
    @ResponseBody
    public Car viewCar(@PathVariable("id") int id) {
        return carService.getCar(id);
    }
}
