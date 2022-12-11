package by.carlab.dao;

import by.carlab.model.ImageCar;

public interface ImageDao {
    void create(ImageCar imageCar);
    void delete(ImageCar imageCar);
    void update(ImageCar imageCar);
}
