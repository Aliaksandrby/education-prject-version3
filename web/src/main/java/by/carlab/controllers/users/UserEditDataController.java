package by.carlab.controllers.users;

import by.carlab.model.User;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserEditDataController {

    @Autowired
    private UserService userService;

    private int userId;

    @GetMapping("/user/editUserData.html")
    public String editUser(Principal principal,Model model) {
        User user = userService.findByUsername(principal.getName());
        userId = user.getId();
        model.addAttribute("user",user);
        return "editUserData";
    }

    @PostMapping("/user/editUserData.html")
    public String editUser(User user,Model model) {
        model.addAttribute("user",userService.editUser(user,userId));
        return "editUserData";
    }
}
