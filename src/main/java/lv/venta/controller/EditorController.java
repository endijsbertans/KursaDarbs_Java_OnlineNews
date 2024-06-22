package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Editor;
import lv.venta.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    private IEditorService editorService;

    @GetMapping("/all")
    public String getAllEditors(Model model) {
        try {
            model.addAttribute("editors", editorService.getAllEditors());
            return "all-editor-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String getEditorAdd(Model model) {
        model.addAttribute("editor", new Editor());
        return "insert-editor-page";
    }

    @PostMapping("/add")
    public String postEditorAdd(@Valid @ModelAttribute("editor") Editor editor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("editor", editor);
            return "insert-editor-page"; 
        }
        try {
            editorService.insertNewEditor(editor);
            return "redirect:/editor/all";
        } catch (Exception e) {
            model.addAttribute("msg", "Error adding editor: " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}")
    public String getEditorUpdate(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("editor", editorService.selectEditorById(id));
            return "update-editor-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postEditorUpdate(@PathVariable("id") long id, @Valid @ModelAttribute("editor") Editor editor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("editor", editor);
            return "update-editor-page";
        }
        try {
            editorService.updateEditorById(id, editor);
            return "redirect:/editor/all";
        } catch (Exception e) {
            model.addAttribute("msg", "Error updating editor: " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeEditor(@PathVariable("id") long id, Model model) {
        try {
            editorService.deleteEditorById(id);
            return "redirect:/editor/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }
}
