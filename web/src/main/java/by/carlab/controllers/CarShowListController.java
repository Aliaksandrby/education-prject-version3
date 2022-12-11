package by.carlab.controllers;

import by.carlab.cars.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarShowListController {

    @Autowired
    private PagingService pagingService;

    @GetMapping("/")
    public String showCarList() {
        return "redirect:/1.html";
    }

    @GetMapping("/{currentPage}.html")
    public String viewCarList(Model model, @PathVariable("currentPage") int currentPage) {
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("startPage",pagingService.getStartPage(currentPage));
        model.addAttribute("endPage",pagingService.getEndPage(currentPage));
        model.addAttribute("numberOfPages",pagingService.getNumberOfPages());
        model.addAttribute("carList",pagingService.getCarPaging(currentPage));
        return "showCarList";
    }
}
