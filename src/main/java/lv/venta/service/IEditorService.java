package lv.venta.service;

import lv.venta.model.Editor;

import java.util.ArrayList;

public interface IEditorService {
    ArrayList<Editor> getAllEditors() throws Exception;
    public abstract Editor selectEditorById(long id) throws Exception;
    public abstract Editor deleteEditorById(long id) throws Exception;
    public abstract Editor insertNewEditor(Editor editor) throws Exception;
    public abstract Editor updateEditorById(long id, Editor editor) throws Exception;
}
