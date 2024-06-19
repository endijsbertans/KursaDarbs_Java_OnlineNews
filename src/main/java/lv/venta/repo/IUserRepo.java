package lv.venta.repo;

import lv.venta.model.RegisteredUser;
import lv.venta.model.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepo extends CrudRepository<RegisteredUser, Long> {
    RegisteredUser findByNameAndSurname(String name, String surname);

}
