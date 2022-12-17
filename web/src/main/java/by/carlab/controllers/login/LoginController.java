package by.carlab.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @PostMapping("/login.html")
    public String loginAdmin() {
        return "redirect:/";
    }
}
