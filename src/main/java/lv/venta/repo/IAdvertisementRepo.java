package lv.venta.repo;

import lv.venta.model.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvertisementRepo extends CrudRepository<Advertisement, Long> {
}
