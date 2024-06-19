package lv.venta.repo;

import lv.venta.model.Advertisement;
import lv.venta.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepo extends CrudRepository<Article, Long>{


}
