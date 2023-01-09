package by.carlab.controllers.pauments;

import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PaymentCreateController { //todo

    @Autowired
    private PaymentService paymentService;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/payment/create.html")
    public String createPayment(Model model, Principal principal) {
        model.addAttribute("sumToPay",paymentService.createPayment(principal.getName()));
        return "showPayment";
    }
}
