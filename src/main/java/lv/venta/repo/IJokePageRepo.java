package lv.venta.repo;

import lv.venta.model.JokePage;
import org.springframework.data.repository.CrudRepository;

public interface IJokePageRepo extends CrudRepository<JokePage, Long> {
}
