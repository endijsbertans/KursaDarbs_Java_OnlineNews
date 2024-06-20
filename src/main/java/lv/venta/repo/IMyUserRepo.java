package lv.venta.repo;

import lv.venta.model.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface IMyUserRepo extends CrudRepository<MyUser, Integer> {

    //būs publiska un abstract pēc noklusejuma
    //SELECT * FROM MY_USER_TABLE WHERE USERNAME=<username>
    MyUser findByUsername(String username);

}
