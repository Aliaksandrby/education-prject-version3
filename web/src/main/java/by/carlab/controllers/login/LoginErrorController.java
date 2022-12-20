package by.carlab.controllers.login;

import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginErrorController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginError.html")
    public String loginError(Model model) {
        model.addAttribute("error", "wrong login or password");
        return "login";
    }
}
