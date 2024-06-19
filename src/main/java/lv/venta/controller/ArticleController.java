package lv.venta.controller;


import jakarta.validation.Valid;
import lv.venta.model.Article;
import lv.venta.model.Event;
import lv.venta.service.IArticleService;
import lv.venta.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;



    @GetMapping("/show/all")
    public String showAllArticles(Model model) {
        try {
            model.addAttribute("myobjs", articleService.getAllArticles());

            model.addAttribute("title", "All articles");
            return "show-all-art";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeArticle(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Article removed");
            model.addAttribute("myobj",articleService.deleteArticleById(id));
            return "redirect:/article/show/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String getArticleAdd(Model model){
        model.addAttribute("title", "Add article");
        model.addAttribute("article", new Article());
        return "insert-article-page";
    }

    @PostMapping("/add")
    public String postArticleAdd(@Valid Article article, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(article);
            System.out.println(result);
            return "insert-event-page";
        }else{
            try {
                articleService.addArticle(article);
                return "redirect:/article/show/all";
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
            model.addAttribute("article", articleService.getArticleById(id));
            return "update-article-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @PostMapping("/update/{id}")
    public String postEventUpdate(@PathVariable("id") long id, @Valid Article article, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(article);
            System.out.println(result);
            model.addAttribute("id", id);
            return "update-article-page";
        }else{
            try {
                articleService.updateArticleById(id, article);
                return "redirect:/article/show/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }

    @GetMapping("/articleById/{id}")
    public String getArticleById(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("title", "Article by ID");
            model.addAttribute("article", articleService.getArticleById(id));
            return "article-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}







