package by.carlab.cars;

import by.carlab.dao.CarDao;
import by.carlab.dao.ImageDao;
import by.carlab.model.Car;
import by.carlab.model.ImageCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;
    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional
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
    @Transactional
    public void deleteCar(int id) {
        Car car = carDao.findById(id);
        carDao.delete(car);
    }

    @Override
    @Transactional
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
