package lv.venta.service.impl;

import lv.venta.model.JokePage;
import lv.venta.repo.IJokePageRepo;
import lv.venta.service.IJokePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JokePageServiceImpl implements IJokePageService {

    @Autowired
    private IJokePageRepo jokePageRepo;

    @Override
    public void addJoke(JokePage jokePage) throws Exception {
        if (jokePage == null) {
            throw new Exception("Something wrong with input");
        }
        JokePage jokePageNew = new JokePage(jokePage.getTitle(), jokePage.getAuthor(), jokePage.getContent());
        jokePageRepo.save(jokePageNew);
    }

    @Override
    public void updateJokeById(long id, JokePage jokePage) throws Exception {
        if (id <= 0) {
            throw new Exception("JokePage id input is wrong");
        }
        JokePage updateJokePage = jokePageRepo.findById(id).get();
        updateJokePage.setTitle(jokePage.getTitle());
        updateJokePage.setAuthor(jokePage.getAuthor());
        updateJokePage.setContent(jokePage.getContent());
        jokePageRepo.save(updateJokePage);
    }

    @Override
    public JokePage deleteJokeById(long id) throws Exception {
        JokePage deleteJoke = getJokeById(id);
        jokePageRepo.delete(deleteJoke);
        return deleteJoke;
    }

    @Override
    public JokePage getJokeById(long id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID input is wrong");
        }

        if (jokePageRepo.findById(id).isPresent()) {
            return jokePageRepo.findById(id).get();
        } else {
            throw new Exception("There is no JokePage with this id");
        }
    }

    @Override
    public ArrayList<JokePage> getAllJoke() throws Exception {
        return (ArrayList<JokePage>) jokePageRepo.findAll();
    }
    @Override
    public JokePage getRandomJoke() throws Exception {
            List<JokePage> allJokes = (List<JokePage>) jokePageRepo.findAll();
        if (allJokes.isEmpty()) {
            throw new Exception("No jokes available");
        }
        Random random = new Random();
        return allJokes.get(random.nextInt(allJokes.size()));
    }
}
