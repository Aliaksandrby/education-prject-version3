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
import java.util.Objects;

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
        if((car.getIsOrder() == 0) && (user.getIsOrder() == 0) && (user.getIsPayment() == 0)) {
            car.setIsOrder(1);
            user.setIsOrder(1);
            user.setIsPayment(1);
            Order order = new Order();
            order.setUser(user);
            order.setCar(car);
            order.setIsPayment(1);
            order.setDateOrder(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT+3"))));
            orderDao.create(order);
        }
        return car;
    }

    @Override
    public Car completeOrder(String username, int carId) {
        Car car = carDao.findById(carId);
        User user = userDao.findByUsername(username);
        Order order = orderDao.findByUsernameAndCar(username,car);
        if((car.getIsOrder() == 1) && (user.getIsOrder() == 1) && (order != null)) {
            Timestamp timeEndOrder = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT+3")));
            car.setIsOrder(0);
            user.setIsOrder(0);
            order.setDateCompleteOrder(timeEndOrder);
            order.setTimeInOrder(evalTimeInOrder(order.getDateOrder(), timeEndOrder));
        }
        return car;
    }

    @Override
    public Order completeAnyOrder(int id) {
        Order order = orderDao.findById(id);
        if(order.getIsPayment()==1) {
            Timestamp timeEndOrder = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT+3")));
            order.setDateCompleteOrder(timeEndOrder);
            order.setTimeInOrder(evalTimeInOrder(order.getDateOrder(), timeEndOrder));
            order.setIsPayment(0);

            order.getCar().setIsOrder(0);
            order.getUser().setIsOrder(0);
            order.getUser().setIsPayment(0);
        }
        return order;
    }

    @Override
    public Order findOrderByUsername(String username) {
        User user = userDao.findByUsername(username);
        Order newOrder = null;
        List<Order> orderList = user.getOrder();
        for (Order order : orderList) {
            if(Objects.equals(order.getDateCompleteOrder(), null)) {
                newOrder = order;
                break;
            }
        }
        return newOrder;
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

    private int evalTimeInOrder(Timestamp timeStart,Timestamp timeEnd) {
        long timeInOrder = ((timeEnd.getTime()-timeStart.getTime())/60000);
        return (int)timeInOrder;
    }
}
