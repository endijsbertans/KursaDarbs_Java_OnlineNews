package lv.venta.service;

import lv.venta.model.Article;
import lv.venta.model.JokePage;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface IJokePageService {

    void addJoke(JokePage jokePage, MultipartFile imageFile) throws Exception;
    void updateJokeById(long id, JokePage jokePage) throws Exception;
    JokePage deleteJokeById(long id)  throws Exception;
    JokePage getJokeById(long id)  throws Exception;
    ArrayList<JokePage> getAllJoke()  throws Exception;
}
