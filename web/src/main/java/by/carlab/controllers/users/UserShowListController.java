package by.carlab.controllers.users;

import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserShowListController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/showUserList.html")
    public String showUserList(Model model) {
        model.addAttribute("userList",userService.readAll());
        return "showUserList";
    }
}
