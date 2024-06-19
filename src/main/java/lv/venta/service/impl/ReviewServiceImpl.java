package lv.venta.service.impl;

import lv.venta.model.Article;
import lv.venta.model.Review;
import lv.venta.repo.IArticleRepo;
import lv.venta.repo.IReviewRepo;
import lv.venta.service.IArticleService;
import lv.venta.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    IReviewRepo reviewRepo;

    @Autowired
    IArticleRepo articleRepo;

    @Override
    public void addReview(Review review) throws Exception{
        if(review == null){
            throw new Exception("Something wrong with input");
        }

        Review newReview = new Review(review.getMessage(), review.getArticle());

        reviewRepo.save(newReview);

    }

    @Override
    public void deleteReview(long id)  throws Exception{
        if(id <= 0){
            throw new Exception("Reviews id input is wrong");
        }

        if(reviewRepo.existsById(id)){
            reviewRepo.deleteById(id);
        }else{
            throw new Exception("Review not found");
        }

    }

    @Override
    public void updateReview(long id, Review review)  throws Exception{
        if(id <= 0){
            throw new Exception("Reviews id input is wrong");
        }

        Review updatedReview = reviewRepo.findById(id).get();
        updatedReview.setMessage(review.getMessage());
        updatedReview.setArticle(review.getArticle());
        reviewRepo.save(updatedReview);


    }

    @Override
    public ArrayList<Review> getReviewsByArticleId(long id) throws Exception {

        if(id <= 0){
            throw new Exception("Article id input is wrong");
        }

        ArrayList<Review> reviews = new ArrayList<>();

        if(articleRepo.existsById(id)){
            Article article = articleRepo.findById(id).get();
            reviews.addAll(article.getReview());
            if(reviews.isEmpty()){
                throw new Exception("No reviews found");
            }

        }else{
            throw new Exception("Article not found");
        }
        return reviews;
    }
}
