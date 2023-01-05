package by.carlab.dao;

import by.carlab.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void create(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> readAll() {
        String query = "from Car";
        return sessionFactory.getCurrentSession().createQuery(query, Car.class).list();
    }

    @Override
    public void update(Car car) {
        sessionFactory.getCurrentSession().update(car);
    }

    @Override
    public void delete(Car car) {
        Car loadedCar = sessionFactory.getCurrentSession().load(Car.class, car.getId());
        sessionFactory.getCurrentSession().delete(loadedCar);
    }

    @Override
    public Car findById(int id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }
}
