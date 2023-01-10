package by.carlab.restControllers;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarShowListRestController {

    @Autowired
    private CarService carService;

    //curl localhost:8080/rent/rest/cars.html
    @GetMapping("/rest/cars.html")
    @ResponseBody
    public List viewCarList() {
        return carService.getCars();
    }
}
