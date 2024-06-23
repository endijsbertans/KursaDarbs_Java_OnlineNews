package lv.venta.service;

import lv.venta.model.JokePage;

import java.util.ArrayList;

public interface IJokePageService {

    void addJoke(JokePage jokePage) throws Exception;
    void updateJokeById(long id, JokePage jokePage) throws Exception;
    JokePage deleteJokeById(long id) throws Exception;
    JokePage getJokeById(long id) throws Exception;
    ArrayList<JokePage> getAllJoke() throws Exception;
    JokePage getRandomJoke() throws Exception; // New method for random joke retrieval
}
