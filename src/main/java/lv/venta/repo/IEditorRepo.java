package lv.venta.repo;

import lv.venta.model.Editor;
import org.springframework.data.repository.CrudRepository;

public interface IEditorRepo extends CrudRepository<Editor, Long> {
    Editor findByNameAndSurname(String name, String surname);
}
