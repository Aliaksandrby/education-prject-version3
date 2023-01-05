package by.carlab.cars;

import by.carlab.dao.CarDao;
import by.carlab.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarShowServiceImpl implements CarShowService {
    @Autowired
    private CarDao carDao;

    @Override
    public Car getCar(int id) {
        return carDao.findById(id);
    }
}
