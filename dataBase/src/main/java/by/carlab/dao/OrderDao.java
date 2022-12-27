package by.carlab.dao;

import by.carlab.model.Order;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    List<Order> readAll();
    void update(Order order);
    void delete(Order order);
    Order findById(int id);
}
