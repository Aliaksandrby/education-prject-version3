package by.carlab.dao;

import by.carlab.model.Car;

import java.util.List;

public interface CarDao {
    void create(Car car);
    List<Car> readAll();
    void update(Car car);
    void delete(Car car);
    Car findById(int id);
}
