package lv.venta.service.impl;

import lv.venta.model.Editor;
import lv.venta.repo.IEditorRepo;
import lv.venta.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorServiceImpl implements IEditorService {

    @Autowired
    private IEditorRepo editorRepo;


    @Override
    public Editor selectEditorById(long id) throws Exception {
        if(id < 0) throw new Exception("Id should be positive");
        if(editorRepo.existsById(id))
        {
            return editorRepo.findById(id).get();
        }
        throw new Exception("Editor with " + id + " is not found");
    }

    @Override
    public Editor deleteEditorById(long id) throws Exception {
        Editor deleteProduct = selectEditorById(id);
        editorRepo.delete(deleteProduct);
        return deleteProduct;
    }

    @Override
    public Editor insertNewEditor(Editor editor) throws Exception {
        if(editor == null) throw new Exception("Company is null");
        Editor editorFromDB = editorRepo.findByNameAndSurname(editor.getName(), editor.getSurname());
        if(editorFromDB != null) {
            throw new Exception("Customer already exists");
        } else {
            return editorRepo.save(editor);
        }
    }

    @Override
    public Editor updateEditorById(long id, Editor editor) throws Exception {
        Editor updateDriver = selectEditorById(id);
        updateDriver.setName(editor.getName());
        updateDriver.setSurname(editor.getSurname());
        return editorRepo.save(updateDriver);
    }
}
