package com.notation.controller;

import com.notation.entity.Eleve;
import com.notation.repository.EleveRepository;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/eleves")
@RequiredArgsConstructor
public class EleveCrudController {

    private final EleveRepository eleveRepository;
    private final NoteRepository noteRepository;

    @PostMapping("/save")
    public String save(@RequestParam(required = false) Long id, @RequestParam String nom) {
        Eleve eleve = (id != null) ? eleveRepository.findById(id).orElse(new Eleve()) : new Eleve();
        eleve.setNom(nom);
        eleveRepository.save(eleve);
        return "redirect:/admin?tab=eleves";
    }

    @GetMapping("/delete/{id}")
    @Transactional // Nécessaire pour les requêtes modifiantes en custom query (deleteByIdEleve)
    public String delete(@PathVariable Long id) {
        // 1. Suppression des notes liées (cascading manuel)
        noteRepository.deleteByIdEleve(id);
        // 2. Suppression de l'élève
        eleveRepository.deleteById(id);
        
        return "redirect:/admin?tab=eleves";
    }
}
