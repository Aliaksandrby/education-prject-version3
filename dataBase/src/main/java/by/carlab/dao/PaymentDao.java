package by.carlab.dao;

import by.carlab.model.Payment;

import java.util.List;

public interface PaymentDao {
    void create(Payment payment);
    List<Payment> readAll();
    void update(Payment payment);
    void delete(Payment payment);
    Payment findById(int id);
}
