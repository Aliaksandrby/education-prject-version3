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
        return null;
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
}
