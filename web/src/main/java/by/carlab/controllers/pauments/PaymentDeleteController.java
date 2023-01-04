package by.carlab.controllers.pauments;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentDeleteController { //todo


    @Secured("ADMIN")
    @GetMapping("/admin/delete/payment/{id}.html")
    public String deletePayment(@PathVariable("id") int id) {

        return "redirect:/";
    }
}
