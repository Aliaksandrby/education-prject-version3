package by.carlab.orders;

import by.carlab.model.Car;

public interface OrderService {
    Car createOrder(String username,int carId);
    Car completeOrder(String username,int carId);
}
