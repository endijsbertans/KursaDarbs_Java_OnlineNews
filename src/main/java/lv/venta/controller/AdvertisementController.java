package lv.venta.controller;

import lv.venta.model.Advertisement;
import lv.venta.service.IAdvertisementService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {
    @Autowired
    private IAdvertisementService advService;
    @GetMapping("/show/all")
    public String showAllAdv(Model model){
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
    @GetMapping("/remove/{id}")
    public String removeEventById(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Adv removed");
            model.addAttribute("myobj", advService.deleteAdvById(id));
            return "redirect:/advertisement/show/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/add")
    public String getAdvAdd(Model model){
        model.addAttribute("title", "Add advertisement");
        model.addAttribute("advertisement", new Advertisement());
        return "insert-adv-page";
    }
    @PostMapping("/add")
    public String postAdvAdd(@Valid Advertisement advertisement, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(advertisement);
            System.out.println(result);
            return "update-adv-page";
        }else{
            try {
                advService.insertNewAdv(advertisement);
                return "redirect:/advertisement/show/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }
    @GetMapping("/update/{id}")
    public String getAdvUpdate(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Update advertisement");
            model.addAttribute("advertisement", advService.selectAdvById(id));
            return "update-adv-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @PostMapping("/update/{id}")
    public String postAdvUpdate(@PathVariable("id") long id, @Valid Advertisement advertisement, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(advertisement);
            System.out.println(result);
            model.addAttribute("id", id);
            return "update-adv-page";
        }else{
            try {
                advService.updateAdvById(id, advertisement);
                return "redirect:/advertisement/show/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }
}
