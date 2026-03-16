package com.notation.controller;

import com.notation.entity.Note;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/notes")
@RequiredArgsConstructor
public class NoteCrudController {

    private final NoteRepository noteRepository;

    @PostMapping("/save")
    public String save(
            @RequestParam(required = false) Long id,
            @RequestParam Long idEleve,
            @RequestParam Long idMatiere,
            @RequestParam Long idCorrecteur,
            @RequestParam Double noteVal) {

        Note note = (id != null) ? noteRepository.findById(id).orElse(new Note()) : new Note();
        note.setIdEleve(idEleve);
        note.setIdMatiere(idMatiere);
        note.setIdCorrecteur(idCorrecteur);
        note.setNote(noteVal);
        
        noteRepository.save(note);
        return "redirect:/admin?tab=notes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/admin?tab=notes";
    }
}
