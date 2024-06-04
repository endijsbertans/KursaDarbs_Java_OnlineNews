package lv.venta.controller;

import lv.venta.service.IAdvertisementService;
import lv.venta.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private IEventService eventService;
    @GetMapping("/show/all")
    public String showAllDrivers(Model model){
        try {
            model.addAttribute("myobjs", eventService.selectAllEvents());

            model.addAttribute("title", "All events");
            return "show-all-events";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}
