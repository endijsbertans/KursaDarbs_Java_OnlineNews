package lv.venta.service.impl;

import lv.venta.model.Editor;
import lv.venta.model.JokePage;
import lv.venta.repo.IJokePageRepo;
import lv.venta.service.IJokePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class JokePageServiceImpl implements IJokePageService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private IJokePageRepo jokePageRepo;


    @Override
    public void addJoke(JokePage jokePage, MultipartFile imageFile) throws Exception {
        if (jokePage == null) {
            throw new Exception("Something wrong with input");
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = saveImage(imageFile);
            jokePage.setImageFilename(filename);
        }

        JokePage jokePageNew = new JokePage(jokePage.getTitle(), jokePage.getAuthor(), jokePage.getContent());
        jokePageRepo.save(jokePageNew);
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String filename = imageFile.getOriginalFilename();
        Path filepath = Paths.get(UPLOAD_DIR, filename);
        Files.createDirectories(filepath.getParent());
        Files.write(filepath, imageFile.getBytes());
        return filename;
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
            JokePage jokePage = jokePageRepo.findById(id).get();
            return jokePage;
        } else {
            throw new Exception("There is no JokePage with this id");
        }
    }

    @Override
    public ArrayList<JokePage> getAllJoke() throws Exception {
        return (ArrayList<JokePage>) jokePageRepo.findAll();
    }
}
