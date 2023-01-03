package by.carlab.controllers.users;

import by.carlab.model.Card;
import by.carlab.users.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CardAddController {

    @Autowired
    private CardService cardService;

    @Secured({"ADMIN","USER"})
    @PostMapping("/card/add/new.html")
    public String cardAdd(Model model, Card card, Principal principal) {
        cardService.create(card,principal);
        model.addAttribute("cardList",cardService.readAll(principal));
        return "showCardList";
    }
}
