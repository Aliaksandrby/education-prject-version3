package by.carlab.controllers.pauments;

import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class PaymentCreateController { //todo

    @Autowired
    private PaymentService paymentService;

    @Secured({"ADMIN","USER"})
    @GetMapping("/payment/create/car/{id}.html")
    public String createPayment(Model model, @PathVariable("id") int id, Principal principal) {
        model.addAttribute("sumToPay",paymentService.createPayment(principal.getName(),id));
        return "showPayment";
    }
}
