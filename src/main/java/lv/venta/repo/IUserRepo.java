package lv.venta.repo;

import lv.venta.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepo extends CrudRepository<User, Long> {
    User findByNameAndSurname(String name, String surname);

}
