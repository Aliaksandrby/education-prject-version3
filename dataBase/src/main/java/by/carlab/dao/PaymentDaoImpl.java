package by.carlab.dao;

import by.carlab.model.Payment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Payment payment) {
        sessionFactory.getCurrentSession().save(payment);
    }

    @Override
    public List<Payment> readAll() {
        String query = "from Payment";
        return sessionFactory.getCurrentSession().createQuery(query, Payment.class).list();
    }

    @Override
    public void update(Payment payment) {
        sessionFactory.getCurrentSession().update(payment);
    }

    @Override
    public void delete(Payment payment) {
        Payment loadedPayment = sessionFactory.getCurrentSession().load(Payment.class, payment.getId());
        sessionFactory.getCurrentSession().delete(loadedPayment);
    }

    @Override
    public Payment findById(int id) {
        return sessionFactory.getCurrentSession().get(Payment.class, id);
    }
}
