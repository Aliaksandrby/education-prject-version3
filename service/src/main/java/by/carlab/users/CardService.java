package by.carlab.users;

import by.carlab.model.Card;

import java.security.Principal;
import java.util.List;

public interface CardService {
    void create(Card card, Principal principal);
    List<Card> readAll(Principal principal);
    void delete(int id);
}
