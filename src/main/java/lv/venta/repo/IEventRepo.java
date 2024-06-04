package lv.venta.repo;

import lv.venta.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IEventRepo extends CrudRepository<Event, Long> {
    Event findByTitleAndPriceAndDescriptionAndStartDate(String title, float price, String description, LocalDateTime startDate);
}
