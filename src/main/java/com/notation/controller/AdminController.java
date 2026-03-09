package com.notation.controller;

import com.notation.entity.Correcteur;
import com.notation.entity.Eleve;
import com.notation.entity.Matiere;
import com.notation.entity.Note;
import com.notation.repository.CorrecteurRepository;
import com.notation.repository.EleveRepository;
import com.notation.repository.MatiereRepository;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EleveRepository eleveRepository;
    private final CorrecteurRepository correcteurRepository;
    private final MatiereRepository matiereRepository;
    private final NoteRepository noteRepository;

    @GetMapping
    public String showAdminPage(
            @RequestParam(name = "tab", defaultValue = "eleves") String tab,
            @RequestParam(name = "editId", required = false) Long editId,
            Model model) {

        // Onglet actif
        model.addAttribute("activeTab", tab);
        model.addAttribute("editId", editId);

        // Charger les entités pour affichage et selects
        List<Eleve> eleves = eleveRepository.findAll();
        List<Correcteur> correcteurs = correcteurRepository.findAll();
        List<Matiere> matieres = matiereRepository.findAll();
        List<Note> notes = noteRepository.findAll();

        model.addAttribute("eleves", eleves);
        model.addAttribute("correcteurs", correcteurs);
        model.addAttribute("matieres", matieres);
        model.addAttribute("notes", notes); // Note : on peut optimiser avec un fetch plus tard

        return "admin";
    }
}
