package lv.venta;

import lv.venta.model.*;
import lv.venta.repo.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collection;

@SpringBootApplication
public class KursaDarbsJavaOnlineNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KursaDarbsJavaOnlineNewsApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabaseLayer(
            IAdvertisementRepo advertisementRepo,
            IEditorRepo editorRepo,
            IEventRepo eventRepo,
            IArticleRepo articleRepo,
            IReviewRepo reviewRepo,
            IPersonRepo personRepo,
            IUserRepo userRepo){

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Person pers = new Person("Daniels", "Kalnas");
                Editor ed1 = new Editor(pers, FieldOfOperation.sport);
                personRepo.save(pers);
                editorRepo.save(ed1);
                System.out.println("All editors: " + editorRepo.findAll());

                Person pers2 = new Person("Endijs", "Bmwe");
                personRepo.save(pers2);

                RegisteredUser user1 = new RegisteredUser(pers2);
                userRepo.save(user1);

                Advertisement ad1 = new Advertisement("Ad1", 1.0f, "Ad1 desc", user1);
                Advertisement ad2 = new Advertisement("Ad2", 2.0f, "Ad2 desc", user1);
                advertisementRepo.save(ad1);
                advertisementRepo.save(ad2);

                System.out.println("All ads: " + advertisementRepo.findAll());
                Event ev1 = new Event("Ev1", 1.0f, "Ev1 desc", 2, user1);
                Event ev2 = new Event("Ev2", 2.0f, "Ev2 desc", 3, user1);
                eventRepo.save(ev1);
                eventRepo.save(ev2);
                System.out.println("All events: " + eventRepo.findAll());




                Article article1 = new Article("Uguns", Genre.Urgent,ed1,"Svētku dienā Juris cepot gaļu nodzedzināja lauku, kas beidzās slikti :(");
                articleRepo.save(article1);

                Review review1 = new Review("Šis ir bēdīgi",article1);
                Review review2 = new Review("Žēl ugunis karsts",article1);
                reviewRepo.save(review1);
                reviewRepo.save(review2);

            }
        };
    }
}