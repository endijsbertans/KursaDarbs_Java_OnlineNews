package lv.venta.service;

import lv.venta.model.Article;

import java.util.ArrayList;
import java.util.List;

public interface IArticleService {
    void addArticle(Article article) throws Exception;
    void updateArticleById(long id,Article article) throws Exception;
    Article deleteArticleById(long id)  throws Exception;
    Article getArticleById(long id)  throws Exception;
    ArrayList<Article> getAllArticles()  throws Exception;

}
