package by.carlab.orders;

import by.carlab.model.Car;
import by.carlab.model.Order;

import java.util.List;

public interface OrderService {
    Car createOrder(String username,int carId);
    Car completeOrder(String username,int carId);
    List<Order> showOrderList();
    Order getOrder(int id);
    void deleteOrder(int id);
    Order completeAnyOrder(int id);
    Order findOrderByUsername(String username);
    boolean isCarInOrder(int carId);
}
