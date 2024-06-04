package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Advertisement;
import lv.venta.model.Event;
import lv.venta.service.IAdvertisementService;
import lv.venta.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private IEventService eventService;
    @GetMapping("/show/all")
    public String showAllEvents(Model model){
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
    @GetMapping("/remove/{id}")
    public String removeEventById(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Event removed");
            model.addAttribute("myobj", eventService.deleteEventById(id));
            return "redirect:/event/show/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/add")
    public String getEventAdd(Model model){
        model.addAttribute("title", "Add advertisement");
        model.addAttribute("event", new Event());
        return "insert-event-page";
    }
    @PostMapping("/add")
    public String postEventAdd(@Valid Event event, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(event);
            System.out.println(result);
            return "insert-event-page";
        }else{
            try {
                eventService.insertNewEvent(event);
                return "redirect:/event/show/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }
    @GetMapping("/update/{id}")
    public String getEventUpdate(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Update event");
            model.addAttribute("event", eventService.selectEventById(id));
            return "update-event-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @PostMapping("/update/{id}")
    public String postEventUpdate(@PathVariable("id") long id, @Valid Event event, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(event);
            System.out.println(result);
            model.addAttribute("id", id);
            return "update-event-page";
        }else{
            try {
                eventService.updateEventById(id, event);
                return "redirect:/event/show/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }
}
