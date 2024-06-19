package lv.venta.controller;

import lv.venta.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editor")

public class EditorController {

    @Autowired
    private IEditorService editorService;

    @GetMapping("/show")
    public String selectEditorById(@RequestParam("id") long id, Model model){
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




}
