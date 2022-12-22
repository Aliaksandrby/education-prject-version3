package by.carlab.dao;

import by.carlab.model.Order;
import by.carlab.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
    @Override
    public void create(Order order) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }
}
