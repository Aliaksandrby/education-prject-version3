package by.carlab.dao;

import by.carlab.model.Card;

import java.util.List;

public interface CardDao {
    void create(Card card);
    List<Card> readAll();
    void delete(int id);

}
