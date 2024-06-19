package lv.venta.service.impl;

import lv.venta.model.Article;
import lv.venta.model.Review;
import lv.venta.repo.IArticleRepo;
import lv.venta.repo.IReviewRepo;
import lv.venta.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IArticleImpl implements IArticleService {

    @Autowired
    private IArticleRepo articleRepo;

    @Autowired
    private IReviewRepo reviewRepo;


    @Override
    public void addArticle(Article article) throws Exception {
        if(article == null){
            throw new Exception("Something wrong with input");
        }

        Article articleNew = new Article(article.getTitle(),article.getGenre(),article.getAuthor(),article.getContent());

        articleRepo.save(articleNew);

    }

    @Override
    public void updateArticleById(long id, Article article) throws Exception {
        if(id <= 0){
            throw new Exception("Drivers id input is wrong");
        }
        Article updatedArticle = articleRepo.findById(id).get();
        updatedArticle.setTitle(article.getTitle());
        updatedArticle.setGenre(article.getGenre());
        updatedArticle.setAuthor(article.getAuthor());
        updatedArticle.setContent(article.getContent());
            articleRepo.save(updatedArticle);

    }

    @Override
    public Article deleteArticleById(long id) throws Exception {
        if(id <= 0){
            throw new Exception("Articles id input is wrong");
        }
        if(articleRepo.existsById(id)){
            Article deletedArticle = articleRepo.findById(id).get();
            List<Review> reviews = deletedArticle.getReview();
            reviewRepo.deleteAll(reviews);
            articleRepo.deleteById(id);
            return deletedArticle;
        }else{
            throw new Exception("Article does not exist");
        }


    }

    @Override
    public Article getArticleById(long id) throws Exception {
        if(id <= 0){
            throw new Exception("ID input is wrong");
        }

        if(articleRepo.findById(id).isPresent()){
            Article article = articleRepo.findById(id).get();
            return article;
        } else{
            throw new Exception("There is no article with this id");
        }
    }

    @Override
    public ArrayList<Article> getAllArticles() throws Exception {
        return (ArrayList<Article>) articleRepo.findAll();
    }


}
