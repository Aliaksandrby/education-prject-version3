package by.carlab.cars;

import by.carlab.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    Car addCar(Car car, MultipartFile[] images)throws IOException;
    void deleteCar(int id);
    Car editCar(Car car, int id, MultipartFile[] images) throws IOException;
    List<Car> getCars();
    Car getCar(int id);
}
