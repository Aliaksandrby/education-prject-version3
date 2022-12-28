package by.carlab.dao;

import by.carlab.model.Car;
import by.carlab.model.Order;
import by.carlab.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
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
    public Order findByCarAndUser(User user, Car car) {
        List<Order> orderList = readAll();
        Order newOrder = null;
        for (Order order : orderList) {
            if(
                    order.getUser().getUsername().equals(user.getUsername()) &&
                    user.getIsOrdered() == 1 &&
                    car.getIsOrdered()==1 &&
                    order.getCar().getName().equals(car.getName()) &&
                    order.getDateCompleteOrder() == null
            ) {
                newOrder = order;
                break;
            }
        }
        return newOrder;
    }
}
