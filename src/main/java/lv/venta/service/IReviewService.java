package lv.venta.service;

import lv.venta.model.Review;

import java.util.ArrayList;

public interface IReviewService {
    void addReview(Review review) throws Exception;
    void deleteReview(long id) throws Exception;
    void updateReview(long id,Review review) throws Exception;
    //Vajag funkciju kas atroda reviews pec user
    ArrayList<Review> getReviewsByArticleId(long id) throws Exception;
}
