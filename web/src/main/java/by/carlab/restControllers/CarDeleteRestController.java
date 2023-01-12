package by.carlab.restControllers;

import by.carlab.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarDeleteRestController {

    @Autowired
    private CarService carService;

    //curl -s http://localhost:8080/rent/rest/admin/delete/car/i.html --user username:password

    @GetMapping("/rest/admin/delete/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
    }
}
