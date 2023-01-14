package by.carlab.cars;

import by.carlab.dao.CarDao;
import by.carlab.dao.ImageDao;
import by.carlab.dao.OrderDao;
import by.carlab.model.Car;
import by.carlab.model.ImageCar;
import by.carlab.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Car addCar(Car car, MultipartFile[] images) throws IOException {
        carDao.create(car);
        if (!(images.length==1 && Objects.equals(images[0].getOriginalFilename(), ""))) {
            List<ImageCar> imageCarList = addImagesToCar(car,images);
            car.setImageCarList(imageCarList);
        } else {
            ImageCar imageCar = new ImageCar();
            imageCar.setCar(car);
            File file = new File("/img/noAuto.png");
            byte[] image = new byte[(int)file.length()];
            imageCar.setImage(Base64.getEncoder().encodeToString(image));
            imageDao.create(imageCar);
            car.setImageCarList(List.of(imageCar));
        }
        return carDao.findById(car.getId());
    }

    @Override
    public void addRestCar(Car car) {
        if(car.getName() == null || car.getName().length() == 0) {
            car.setName("Tesla");
        }
        if(car.getYear() < 1900) {
            car.setYear(2023);
        }

        if(car.getEngineDescription() == null || car.getEngineDescription().length() == 0) {
            car.setEngineDescription("85kw");
        }

        if(car.getTransmission() == null || car.getTransmission().length() == 0) {
            car.setTransmission("automatic");
        }

        if(car.getPrice() < 0) {
            car.setPrice(5);
        }

        car.setOrderList(null);
        car.setIsOrder(0);

        ImageCar imageCar = new ImageCar();
        imageCar.setCar(car);
        File file = new File("/img/noAuto.png");
        byte[] image = new byte[(int)file.length()];
        imageCar.setImage(Base64.getEncoder().encodeToString(image));
        imageDao.create(imageCar);
        car.setImageCarList(List.of(imageCar));

        carDao.create(car);
    }

    @Override
    public void deleteCar(int id) {
        Car car = carDao.findById(id);
        List<Order> orderList = orderDao.readAll();
        for (Order order : orderList) {
            if(order.getCar() != null) {
                if(Objects.equals(order.getCar().getId(), car.getId()) ) {
                    order.setCar(null);
                    orderDao.update(order);
                }
            }
        }
        carDao.delete(car);
    }

    @Override
    public Car editCar(Car car, int id, MultipartFile[] images) throws IOException {
        Car car1 = carDao.findById(id);
        car1.setName(car.getName());
        car1.setYear(car.getYear());
        car1.setEngineDescription(car.getEngineDescription());
        car1.setTransmission(car.getTransmission());
        car1.setPrice(car.getPrice());
        carDao.update(car1);
        if (!(images.length == 1 && Objects.equals(images[0].getOriginalFilename(), ""))) {
            deleteAllImagesFromTheCar(car1);
            List<ImageCar> imageCarList = addImagesToCar(car1,images);
            car1.setImageCarList(imageCarList);
        }
        return carDao.findById(car1.getId());
    }

    @Override
    public void editRestCar(int id, String name, Integer year, String engineDescription, String transmission, Double price) {
        Car car = carDao.findById(id);

        if(name != null) {
            if(name.length() > 0) car.setName(name);
        }

        if(year != null) {
            if(year > 1900) car.setYear(year);
        }

        if(engineDescription != null) {
            if(engineDescription.length() > 0) car.setEngineDescription(engineDescription);
        }

        if(transmission != null) {
            if(transmission.length() > 0) car.setTransmission(transmission);
        }

        if(price != null) {
            if(price > 1) car.setPrice(price);
        }
    }

    @Override
    public List<Car> getCars() {
        return carDao.readAll();
    }

    @Override
    public Car getCar(int id) {
        return carDao.findById(id);
    }

    private void deleteAllImagesFromTheCar(Car car) {
        for (ImageCar ic : car.getImageCarList()) {
            imageDao.delete(ic);
        }
    }

    private List<ImageCar> addImagesToCar(Car car,MultipartFile[] images) throws IOException{
        List<ImageCar> imageCarList = new ArrayList<>();
        for (MultipartFile image : images) {
            ImageCar imageCar = new ImageCar();
            imageCar.setCar(car);
            imageCar.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            imageCarList.add(imageCar);
            imageDao.create(imageCar);
        }
        return imageCarList;
    }
}
