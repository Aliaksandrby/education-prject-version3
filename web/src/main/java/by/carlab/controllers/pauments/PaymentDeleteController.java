package by.carlab.controllers.pauments;


import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentDeleteController { //todo

    @Autowired
    private PaymentService paymentService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/delete/payment/{id}.html")
    public String deletePayment(Model model, @PathVariable("id") int id) {
        paymentService.delete(id);
        model.addAttribute("paymentList",paymentService.readAll());
        return "showPaymentList";
    }
}
