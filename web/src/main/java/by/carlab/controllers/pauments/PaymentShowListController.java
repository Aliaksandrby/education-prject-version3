package by.carlab.controllers.pauments;

import by.carlab.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentShowListController { //todo

    @Autowired
    private PaymentService paymentService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/showPaymentList.html")
    public String showPaymentList(Model model) {
        model.addAttribute("paymentList",paymentService.readAll());
        return "showPaymentList";
    }
}
