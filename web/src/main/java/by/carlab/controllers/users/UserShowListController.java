package by.carlab.controllers.users;

import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserShowListController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/admin/showUserList.html")
    public String showUserList(Model model) {
        model.addAttribute("userList",userService.readAll());
        return "showUserList";
    }
}
