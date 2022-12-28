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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        if(car.getIsOrdered() == 0 && user.getIsOrdered() == 0) {
            Order order = new Order();
            order.setCar(car);
            order.setUser(user);
            order.setDateOrder(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT+3"))));
            car.setIsOrdered(1);
            user.setIsOrdered(1);
            carDao.update(car);
            userDao.update(user);
            orderDao.create(order);
        }
        return car;
    }

    @Override
    public Car completeOrder(String username, int carId) {
        Car car = carDao.findById(carId);
        User user = userDao.findByUsername(username);
        Order order = orderDao.findByCarAndUser(user,car);
        if(car.getIsOrdered() == 1 && user.getIsOrdered() == 1 && order != null) {
            car.setIsOrdered(0);
            user.setIsOrdered(0);
            order.setDateCompleteOrder(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT+3"))));
            carDao.update(car);
            userDao.update(user);
            orderDao.update(order);
        }
        return car;
    }

    @Override
    public List<Order> showOrderList() {
        return orderDao.readAll();
    }

    @Override
    public Order getOrder(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void deleteOrder(int id) {
        Order foundOrder = orderDao.findById(id);
        orderDao.delete(foundOrder);
    }


}
