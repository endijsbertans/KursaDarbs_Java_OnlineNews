package lv.venta.controller;

import lv.venta.service.IAdvertisementService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {
    @Autowired
    private IAdvertisementService advService;
    @GetMapping("/show/all")
    public String showAllDrivers(Model model){
        try {
            model.addAttribute("myobjs", advService.selectAllAdv());

            model.addAttribute("title", "All adverts");
            return "show-all-adv";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}
