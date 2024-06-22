package lv.venta.service.impl;

import lv.venta.model.Editor;
import lv.venta.repo.IEditorRepo;
import lv.venta.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EditorServiceImpl implements IEditorService {

    @Autowired
    private IEditorRepo editorRepo;

    @Override
    public ArrayList<Editor> getAllEditors() {
        return (ArrayList<Editor>) editorRepo.findAll();
    }

    @Override
    public Editor selectEditorById(long id) throws Exception {
        if (id < 0) throw new Exception("Id should be positive");
        Optional<Editor> optionalEditor = editorRepo.findById(id);
        if (optionalEditor.isPresent()) {
            return optionalEditor.get();
        }
        throw new Exception("Editor with ID " + id + " is not found");
    }

    @Override
    public Editor deleteEditorById(long id) throws Exception {
        Editor deleteEditor = selectEditorById(id);
        editorRepo.delete(deleteEditor);
        return deleteEditor;
    }

    @Override
    public Editor insertNewEditor(Editor editor) throws Exception {
        if (editor == null) throw new Exception("Editor is null");
        Editor editorFromDB = editorRepo.findByUsername(editor.getUsername());
        if (editorFromDB != null) {
            throw new Exception("Editor already exists");
        } else {
            return editorRepo.save(editor);
        }
    }

    @Override
    public Editor updateEditorById(long id, Editor editor) throws Exception {
        Editor updateEditor = selectEditorById(id);
        updateEditor.setName(editor.getName());
        updateEditor.setSurname(editor.getSurname());
        updateEditor.setUsername(editor.getUsername());
        updateEditor.setPassword(editor.getPassword());
        updateEditor.setFieldOfOperation(editor.getFieldOfOperation());
        return editorRepo.save(updateEditor);
    }
}
