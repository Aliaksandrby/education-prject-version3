package by.carlab.payments;

import by.carlab.dao.OrderDao;
import by.carlab.dao.PaymentDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Order;
import by.carlab.model.Payment;
import by.carlab.model.User;
import by.carlab.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public double createPayment(String username, int carId) { // todo null if didn't order
        Order order = orderService.completeOrder(username, carId);
        return order.getCar().getPrice()*order.getTimeInOrder();
    }

    @Override
    public void completePayment(String username,Payment payment) {
        User user = userDao.findByUsername(username);
        user.setIsPayment(0);
        payment.setUser(user);
        paymentDao.create(payment);
        userDao.update(user);
    }

    @Override
    public List<Payment> readAll() {
        return paymentDao.readAll();
    }
}
