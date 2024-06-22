package lv.venta.repo;

import lv.venta.model.RegisteredUser;
import lv.venta.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRegisteredUserRepo extends CrudRepository<Review, Long>{

}
