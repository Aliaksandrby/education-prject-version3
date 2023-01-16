package by.carlab.controllers.payments;

import by.carlab.model.Payment;
import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PaymentCompleteController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/complete.html")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String completePayment(Payment payment, Principal principal) {
        paymentService.completePayment(principal.getName(),payment);
        return "redirect:/";
    }
}
