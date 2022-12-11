package by.carlab.controllers;

import by.carlab.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String login() {
        return "loginForm";
    }

    @PostMapping("/login.html")
    public String login(User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "redirect:/1.html";
    }
}
