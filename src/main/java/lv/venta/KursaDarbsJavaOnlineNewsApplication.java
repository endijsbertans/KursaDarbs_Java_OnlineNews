package lv.venta;

import lv.venta.model.*;
import lv.venta.repo.IAdvertisementRepo;
import lv.venta.repo.IEditorRepo;
import lv.venta.repo.IEventRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class KursaDarbsJavaOnlineNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KursaDarbsJavaOnlineNewsApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabaseLayer(
            IAdvertisementRepo advertisementRepo,
            IEventRepo eventRepo, IEditorRepo editorRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Advertisement ad1 = new Advertisement("Ad1", 1.0f, "Ad1 desc");
                Advertisement ad2 = new Advertisement("Ad2", 2.0f, "Ad2 desc");
                advertisementRepo.save(ad1);
                advertisementRepo.save(ad2);
                System.out.println("All ads: " + advertisementRepo.findAll());
                Event ev1 = new Event("Ev1", 1.0f, "Ev1 desc", 2);
                Event ev2 = new Event("Ev2", 2.0f, "Ev2 desc", 3);
                eventRepo.save(ev1);
                eventRepo.save(ev2);
                System.out.println("All events: " + eventRepo.findAll());

                Person pers = new Person("Daniels","12345-12345", "Kalnas");
                Editor ed1 = new Editor(pers, FieldOfOperation.sport);
                editorRepo.save(ed1);
                System.out.println("All editors: " + editorRepo.findAll());
            }
        };
    }
}