package by.carlab.controllers.users;

import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserShowController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/user/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String showUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user",userService.findById(id));
        return "showUser";
    }
}
