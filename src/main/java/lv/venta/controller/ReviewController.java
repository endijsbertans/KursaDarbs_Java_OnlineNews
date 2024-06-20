package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Article;
import lv.venta.model.Review;
import lv.venta.repo.IReviewRepo;
import lv.venta.service.IArticleService;
import lv.venta.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/review")
public class ReviewController {


    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IArticleService articleService;


    @GetMapping("/remove/{id}")
    public String removeReview(@PathVariable("id") long id, Model model) {
        try {

            long articleId = reviewService.FindArticleIDByReviewId(id);

            model.addAttribute("articleId", articleId);
            model.addAttribute("title", "Review removed");
            model.addAttribute("myobj", reviewService.deleteReview(id));


            return "redirect:/article/articleById/" + articleId;
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }


    @GetMapping("/add/{articleId}")
    public String getReviewAdd(@PathVariable("articleId") long articleId, Model model) {
        model.addAttribute("title", "Add Review");
        model.addAttribute("review", new Review());
        model.addAttribute("articleId", articleId);
        return "insert-review-page";
    }

    @PostMapping("/add")
    public String postReviewAdd(@RequestParam("articleId") long articleId, @Valid Review review, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(review);
            System.out.println(result);
            return "insert-review-page";
        } else {
            try {

                Article article = articleService.getArticleById(articleId);
                review.setArticle(article);
                review.setDatePosted(LocalDate.now());
                reviewService.addReview(review);
                return "redirect:/article/articleById/" + articleId;
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }


    @GetMapping("/update/{id}")
    public String getReviewUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            Review review = reviewService.getReviewById(id);
            long articleId = reviewService.FindArticleIDByReviewId(id);
            model.addAttribute("title", "Update Review");
            model.addAttribute("review", review);
            model.addAttribute("articleId", articleId);
            return "update-review-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }


    @PostMapping("/update/{id}")
    public String postReviewUpdate(@PathVariable("id") long id, @ModelAttribute("review") @Valid Review review,
                                   BindingResult result, @RequestParam("articleId") long articleId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-review-page";
        } else {
            try {


                review.setDatePosted(LocalDate.now());
                review.setArticle(articleService.getArticleById(articleId));
                reviewService.updateReview(id, review);
                return "redirect:/article/articleById/" + articleId;
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }
        }
    }

}






