package by.carlab.dao;

import by.carlab.model.Order;
import by.carlab.model.User;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    List<User> readAll();
    void update(Order order);
    void delete(int id);
}
