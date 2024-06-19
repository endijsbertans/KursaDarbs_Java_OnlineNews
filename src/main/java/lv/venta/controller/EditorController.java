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
            model.addAttribute("title", "All Editors");
            return "all-editor-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/show")
    public String selectEditorById(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("title", "Editor by ID");
            model.addAttribute("myobj", editorService.selectEditorById(id));
            return "show-editor-id";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String getEditorAdd(Model model) {
        model.addAttribute("title", "Add Editor");
        model.addAttribute("editor", new Editor());
        return "insert-editor-page";
    }

    @PostMapping("/add")
    public String postEditorAdd(@Valid @ModelAttribute("editor") Editor editor, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "insert-editor-page";
        } else {
            try {
                editorService.insertNewEditor(editor);
                return "redirect:/editor/all";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }

    @GetMapping("/update/{id}")
    public String getEditorUpdate(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("title", "Update Editor");
            model.addAttribute("editor", editorService.selectEditorById(id));
            return "update-editor-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postDriverUpdate(@PathVariable("id") long id, @Valid Editor editor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-editor-page";
        } else {
            try {
                editorService.updateEditorById(id, editor);
                return "redirect:/editor/all";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }


    @GetMapping("/remove/{id}")
    public String deleteEditorById(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("title", "Editor removed");
            model.addAttribute("myobj", editorService.deleteEditorById(id));
            return "redirect:/editor/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}





