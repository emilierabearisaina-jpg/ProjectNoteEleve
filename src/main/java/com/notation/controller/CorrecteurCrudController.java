package com.notation.controller;

import com.notation.entity.Correcteur;
import com.notation.repository.CorrecteurRepository;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/correcteurs")
@RequiredArgsConstructor
public class CorrecteurCrudController {

    private final CorrecteurRepository correcteurRepository;
    private final NoteRepository noteRepository;

    @PostMapping("/save")
    public String save(@RequestParam(required = false) Long id, @RequestParam String nom) {
        Correcteur correcteur = (id != null) ? correcteurRepository.findById(id).orElse(new Correcteur()) : new Correcteur();
        correcteur.setNom(nom);
        correcteurRepository.save(correcteur);
        return "redirect:/admin?tab=correcteurs";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Long id) {
        noteRepository.deleteByIdCorrecteur(id);
        correcteurRepository.deleteById(id);
        return "redirect:/admin?tab=correcteurs";
    }
}
