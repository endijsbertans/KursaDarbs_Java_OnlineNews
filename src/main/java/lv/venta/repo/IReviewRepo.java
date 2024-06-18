package lv.venta.repo;

import lv.venta.model.Article;
import lv.venta.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepo  extends CrudRepository<Review, Long> {
}
