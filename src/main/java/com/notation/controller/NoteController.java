package com.notation.controller;

import com.notation.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/")
    public String index(@RequestParam(required = false) Long idCandidat, Model model) {
        if (idCandidat != null) {
            try {
                Map<String, Object> resultat = noteService.getResultatEleve(idCandidat);
                model.addAttribute("resultat", resultat);
            } catch (RuntimeException e) {
                model.addAttribute("erreur", e.getMessage());
            }
            model.addAttribute("idCandidat", idCandidat);
        }
        return "index";
    }
}
