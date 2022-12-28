package by.carlab.dao;

import by.carlab.model.Car;
import by.carlab.model.Order;
import by.carlab.model.User;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    List<Order> readAll();
    void update(Order order);
    void delete(Order order);
    Order findById(int id);
    Order findByCarAndUser(User user, Car car);
}
