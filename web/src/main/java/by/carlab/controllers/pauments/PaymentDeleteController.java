package by.carlab.controllers.pauments;

import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentDeleteController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/admin/delete/payment/{id}.html")
    @Secured(value = {"ROLE_ADMIN"})
    public String deletePayment(Model model, @PathVariable("id") int id) {
        paymentService.delete(id);
        model.addAttribute("paymentList",paymentService.readAll());
        return "showPaymentList";
    }
}
