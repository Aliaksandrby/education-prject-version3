package by.carlab.restControllers;

import by.carlab.cars.CarService;
import by.carlab.model.Car;
import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarRestController {

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    //curl localhost:8080/rent/rest/cars.html
    @GetMapping("/rest/cars.html")
    public List<Car> getCars() {
        return carService.getCars();
    }

    //curl localhost:8080/rent/rest/car/carId.html
    @GetMapping("/rest/car/{id}.html")
    public Car getCar(@PathVariable("id") int id) {
        return carService.getCar(id);
    }

    //curl -X DELETE http://localhost:8080/rent/rest/admin/delete/car/i.html --user admin:admin
    @DeleteMapping("/rest/admin/delete/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void deleteCar(@PathVariable("id") int id) {
        if(!orderService.isCarInOrder(id)) {
            carService.deleteCar(id);
        }
    }

    // curl  -H 'Content-Type: application/json' --data '{"name":"Tesla","year":2014,"engineDescription":"85kW","transmission":"automatic","price":5.0,"orderList":[],"isOrder":0,"imageCarList":[{"image":""}]}' http://localhost:8080/rent/rest/admin/add/car.html --user admin:admin
    @PostMapping("/rest/admin/add/car.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void createCar(@RequestBody Car car) {
        carService.addRestCar(car);
    }

    //curl -X PUT "http://localhost:8080/rent/rest/admin/edit/car/34.html?year=2011&name=aaaaaaaa&engineDescription=tutu" --user admin:admin
    @PutMapping("/rest/admin/edit/car/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void editCar(
            @PathVariable("id") int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String engineDescription,
            @RequestParam(required = false) String transmission,
            @RequestParam(required = false) Double price
    ) {
        carService.editRestCar(id,name,year,engineDescription,transmission,price);
    }
}
