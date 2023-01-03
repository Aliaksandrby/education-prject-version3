package by.carlab.controllers.users;

import by.carlab.users.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CardListShowController {

    @Autowired
    private CardService cardService;

    @Secured({"ADMIN","USER"})
    @GetMapping("/card/showCardList.html")
    public String showCardList(Model model, Principal principal) {
        model.addAttribute("cardList",cardService.readAll(principal));
        return "showCardList";
    }
}
