package by.carlab.restControllers;

import by.carlab.dao.UserDao;
import by.carlab.model.Car;
import by.carlab.model.User;
import by.carlab.orders.OrderService;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    //curl localhost:8080/rent/rest/admin/users.html -u admin:admin
    @GetMapping("/rest/admin/users.html")
    @Secured(value = {"ROLE_ADMIN"})
    public List<User> getUsers() {
        return userService.readAll();
    }


    //curl localhost:8080/rent/rest/admin/user/3.html -u admin:admin
    @GetMapping("/rest/admin/user/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public User getUser(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    //curl -X DELETE localhost:8080/rent/rest/admin/delete/user/3.html -u admin:admin
    @DeleteMapping("/rest/admin/delete/user/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void deleteUser(@PathVariable("id") int id) {
        if(!orderService.isUserInOrder(id)) {
            userService.deleteUser(id);
        }
    }

    @PostMapping(path = "/rest/add/user.html")
    @Secured(value = {"ROLE_ADMIN"})
    public void createUser(@ModelAttribute User user) {
        userService.createUser(user);
    }

}
