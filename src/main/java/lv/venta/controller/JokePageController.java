package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.JokePage;
import lv.venta.service.IJokePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/joke")
public class JokePageController {

    @Autowired
    private IJokePageService jokeService;

    @GetMapping("/all")
    public String showAllJokes(Model model, Principal principal) {
        try {
            if (isAdmin(principal)) {
                model.addAttribute("myobjs", jokeService.getAllJoke());
                model.addAttribute("title", "All jokes");
                return "all-jokes-page";
            } else {
                model.addAttribute("myobj", jokeService.getRandomJoke());
                model.addAttribute("title", "Random Joke");
                return "random-joke-page";
            }
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeJoke(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("title", "Jokes removed");
            model.addAttribute("myobj", jokeService.deleteJokeById(id));
            return "redirect:/joke/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String getJokeAdd(Model model) {
        model.addAttribute("title", "Add Joke");
        model.addAttribute("joke", new JokePage());
        return "insert-joke-page";
    }

    @PostMapping("/add")
    public String postJokeAdd(@Valid JokePage jokePage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "insert-joke-page";
        } else {
            try {
                jokeService.addJoke(jokePage);
                return "redirect:/joke/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }

    @GetMapping("/update/{id}")
    public String getJokeUpdate(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("title", "Update Joke");
            model.addAttribute("joke", jokeService.getJokeById(id));
            return "update-joke-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postJokeUpdate(@PathVariable("id") long id, @Valid JokePage jokePage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-joke-page";
        } else {
            try {
                jokeService.updateJokeById(id, jokePage);
                return "redirect:/joke/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }



    private boolean isAdmin(Principal principal) {
        return principal != null && principal.getName().equals("admin");
    }
}
