package by.carlab.controllers.pauments;

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

    @Secured({"ADMIN","USER"})
    @PostMapping("/payment/complete.html")
    public String completePayment(Payment payment, Principal principal) {
        paymentService.completePayment(principal.getName(),payment);
        return "redirect:/";
    }
}
