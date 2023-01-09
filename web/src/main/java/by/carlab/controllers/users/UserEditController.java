package by.carlab.controllers.users;

import by.carlab.model.User;
import by.carlab.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEditController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/edit/user/{id}.html")
    public String editUser(User user,int roleId, Model model, @PathVariable("id") int id) {
        model.addAttribute("user",userService.editUser(user,id,roleId));
        return "showUser";
    }
}
