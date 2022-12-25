package by.carlab.orders;

import by.carlab.dao.CarDao;
import by.carlab.dao.OrderDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Car;
import by.carlab.model.Order;
import by.carlab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Car createOrder(String username,int carId) {
        Car car = carDao.findById(carId);
        User user = userDao.findByUsername(username);
        if(car.getIsOrdered() == 0) {
            Order order = new Order();
            order.setCar(car);
            order.setUser(user);
            order.setDateNow(Date.valueOf(LocalDate.now()));
            orderDao.create(order);
            car.setIsOrdered(1);
            carDao.update(car);
        }
        return car;
    }

    @Override
    public Car cancelOrder(String username, int carId) {
        Car car = carDao.findById(carId);
        User user = userDao.findByUsername(username);
        if(car.getIsOrdered() == 1) {
            car.setIsOrdered(0);
            carDao.update(car);
        }
        return car;
    }
}
