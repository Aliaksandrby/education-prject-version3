package by.carlab.dao;

import by.carlab.model.Car;
import by.carlab.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public List<Order> readAll() {
        String query = "from Order";
        return sessionFactory.getCurrentSession().createQuery(query, Order.class).list();
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    public void delete(Order order) {
        Order loadedOrder = sessionFactory.getCurrentSession().load(Order.class, order.getId());
        sessionFactory.getCurrentSession().delete(loadedOrder);
    }

    @Override
    public Order findById(int id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public Order findByUsernameAndCar(String username, Car car) {
        List<Order> orderList = readAll();
        Order newOrder = null;
        for (Order order : orderList) {
            if((order.getUser() == null) || (order.getCar() == null)) {
                continue;
            }
            if(
                    (order.getUser().getUsername().equals(username)) &&
                    (order.getDateCompleteOrder() == null) &&
                    (Objects.equals(order.getCar().getId(), car.getId()))

            ) {
                newOrder = order;
                break;
            }
        }
        return newOrder;
    }

    @Override
    public Order findByUsernameAndIsPayment(String username) {
        List<Order> orderList = readAll();
        Order newOrder = null;
        for (Order order : orderList) {
            if((order.getUser() == null)) {
                continue;
            }
            if(
                    (order.getUser().getUsername().equals(username)) &&
                    (order.getIsPayment() == 1)

            ) {
                newOrder = order;
                break;
            }
        }
        return newOrder;
    }
}
