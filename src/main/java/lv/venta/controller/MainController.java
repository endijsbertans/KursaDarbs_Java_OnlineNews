package lv.venta.controller;

import lv.venta.service.IAdvertisementService;
import lv.venta.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Controller
public class MainController {
    @Autowired
    private IEventService eventService;
    @Autowired
    private IAdvertisementService advService;

    @GetMapping("/") // localhost:8080/
    public String getPage(Model model) {
        try {
            model.addAttribute("title", "Hello to news Page! ");
            model.addAttribute("msg", LocalDateTime.now().toString());
            return "main-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}
