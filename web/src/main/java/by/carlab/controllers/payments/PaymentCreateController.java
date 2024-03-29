package by.carlab.controllers.payments;

import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PaymentCreateController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/create.html")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String createPayment(Model model, Principal principal) {
        model.addAttribute("sumToPay",paymentService.createPayment(principal.getName()));
        return "showPayment";
    }
}
