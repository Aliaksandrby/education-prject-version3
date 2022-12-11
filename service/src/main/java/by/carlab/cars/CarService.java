package by.carlab.cars;

import by.carlab.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarService {
    Car addCar(Car car, MultipartFile[] images)throws IOException;
    void deleteCar(int id);
    Car editCar(Car car, int id, MultipartFile[] images) throws IOException;
}
