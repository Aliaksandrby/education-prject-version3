package by.carlab.payments;

import by.carlab.model.Payment;

import java.util.List;

public interface PaymentService {
    double createPayment(String username);
    void completePayment(String username,Payment payment);
    List<Payment> readAll();
    void delete(int id);
}
