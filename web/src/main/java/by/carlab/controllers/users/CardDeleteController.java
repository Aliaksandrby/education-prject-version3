package by.carlab.controllers.users;

import by.carlab.users.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CardDeleteController {

    @Autowired
    private CardService cardService;

    @Secured({"ADMIN","USER"})
    @GetMapping("/card/delete/{id}.html")
    public String deleteCard(Model model, Principal principal, @PathVariable("id") int id) {
        cardService.delete(id);
        model.addAttribute("cardList",cardService.readAll(principal));
        return "showCardList";
    }
}