package by.carlab.dao;

import by.carlab.model.Car;
import by.carlab.model.Card;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CardDaoImpl implements CardDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Card card) {
        sessionFactory.getCurrentSession().save(card);
    }

    @Override
    public List<Card> readAll() {
        String query = "from Card";
        return sessionFactory.getCurrentSession().createQuery(query, Card.class).list();
    }

    @Override
    public void delete(int id) {
        Card loadedCard = sessionFactory.getCurrentSession().load(Card.class, id);
        sessionFactory.getCurrentSession().delete(loadedCard);
    }
}
