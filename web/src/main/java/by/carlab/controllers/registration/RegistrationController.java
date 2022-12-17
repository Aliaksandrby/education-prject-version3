package by.carlab.controllers.registration;

import by.carlab.model.User;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration.html")
    public String registration() {
        return "registration";
    }

    @PostMapping ("/registration.html")
    public String registration(User user) {
        System.out.println("##################################################################");



        userService.createUser(user);
        System.out.println("##################################################################");
        return "redirect:/";
    }
}

