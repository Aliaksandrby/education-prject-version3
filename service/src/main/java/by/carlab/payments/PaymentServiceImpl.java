package by.carlab.payments;

import by.carlab.dao.OrderDao;
import by.carlab.dao.PaymentDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Order;
import by.carlab.model.Payment;
import by.carlab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public double createPayment(String username) {
        Order order = orderDao.findByUsernameAndIsPayment(username);
        if(order == null) {
            return 0;
        }
        return order.getCar().getPrice()*order.getTimeInOrder();
    }

    @Override
    public void completePayment(String username,Payment payment) {
        User user = userDao.findByUsername(username);
        Order order = orderDao.findByUsernameAndIsPayment(username);
        user.setIsPayment(0);
        order.setIsPayment(0);
        payment.setUser(user);
        paymentDao.create(payment);
        userDao.update(user);
    }

    @Override
    public List<Payment> readAll() {
        return paymentDao.readAll();
    }
}
