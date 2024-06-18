package lv.venta.repo;

import lv.venta.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepo extends CrudRepository<Person, Long> {


}
