package by.carlab.controllers.users;

import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserDeleteController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/delete/user/{id}.html")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/showUserList.html";
    }
}
