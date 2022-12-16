package by.carlab.controllers;

import by.carlab.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration.html")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration.html")
    public String registration(User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getConfirmPassword());
        return "redirect:/";
    }
}
