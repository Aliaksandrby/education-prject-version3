package by.carlab.users;

import by.carlab.dao.CardDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Card;
import by.carlab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public void create(Card card, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        card.setUser(user);
        cardDao.create(card);
    }

    @Override
    public List<Card> readAll(Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        List<Card> cardList = cardDao.readAll();
        List<Card> newCardList = new ArrayList<>();
        for (Card card : cardList) {
            if(Objects.equals(card.getUser().getId(), user.getId())) {
                newCardList.add(card);
            }
        }
        return newCardList;
    }

    @Override
    public void delete(int id) {
        cardDao.delete(id);
    }
}
