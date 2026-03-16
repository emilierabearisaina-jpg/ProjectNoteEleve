package com.notation.controller;

import com.notation.entity.Matiere;
import com.notation.repository.MatiereRepository;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/matieres")
@RequiredArgsConstructor
public class MatiereCrudController {

    private final MatiereRepository matiereRepository;
    private final NoteRepository noteRepository;

    @PostMapping("/save")
    public String save(@RequestParam(required = false) Long id, @RequestParam String nom) {
        Matiere matiere = (id != null) ? matiereRepository.findById(id).orElse(new Matiere()) : new Matiere();
        matiere.setNom(nom);
        matiereRepository.save(matiere);
        return "redirect:/admin?tab=matieres";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Long id) {
        noteRepository.deleteByIdMatiere(id);
        matiereRepository.deleteById(id);
        return "redirect:/admin?tab=matieres";
    }
}
