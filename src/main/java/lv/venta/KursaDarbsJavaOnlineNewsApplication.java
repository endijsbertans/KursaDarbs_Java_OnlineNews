package lv.venta;

import lv.venta.model.*;
import lv.venta.repo.*;

import lv.venta.service.impl.ReviewServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

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
            IMyAuthorityRepo authRepo, IMyUserRepo userRepo, ReviewServiceImpl test){

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

                Article article1 = new Article("Uguns", Genre.Urgent,ed1,"Svētku dienā Juris cepot gaļu nodzedzināja lauku, kas beidzās slikti :(");
                articleRepo.save(article1);

                Review review1 = new Review("Šis ir bēdīgi",article1);
                Review review2 = new Review("Žēl ugunis karsts",article1);
                reviewRepo.save(review1);
                reviewRepo.save(review2);



                MyAuthority a1 = new MyAuthority("ADMIN");
                authRepo.save(a1);
                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                MyUser u1 = new MyUser("admin", encoder.encode("123456"), a1);
                userRepo.save(u1);
                a1.addUser(u1);
                authRepo.save(a1);
                System.out.println("All users: " + userRepo.findAll());
                System.out.println(article1);

            }
        };
    }
}