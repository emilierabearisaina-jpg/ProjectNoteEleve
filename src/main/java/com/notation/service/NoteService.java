package com.notation.service;

import com.notation.entity.Eleve;
import com.notation.entity.Note;
import com.notation.entity.NoteFinale;
import com.notation.repository.EleveRepository;
import com.notation.repository.NoteFinaleRepository;
import com.notation.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final EleveRepository eleveRepository;
    private final NoteFinaleRepository noteFinaleRepository;
    private final NoteRepository noteRepository;

    public Map<String, Object> getResultatEleve(Long idEleve) {

        Eleve eleve = eleveRepository.findById(idEleve)
                .orElseThrow(() -> new RuntimeException("Élève introuvable avec l'ID : " + idEleve));

        List<NoteFinale> notesFinales = noteFinaleRepository.findByIdEleve(idEleve);

        List<Note> notesDetail = noteRepository.findByIdEleveWithDetails(idEleve);

        Map<Long, List<Note>> notesByMatiere = notesDetail.stream()
                .collect(Collectors.groupingBy(Note::getIdMatiere));

        List<Map<String, Object>> matieres = notesFinales.stream()
                .map(nf -> {
                    List<Map<String, Object>> details = notesByMatiere
                            .getOrDefault(nf.getIdMatiere(), List.of())
                            .stream()
                            .map(n -> {
                                Map<String, Object> detail = new LinkedHashMap<>();
                                detail.put("nomCorrecteur", n.getCorrecteur().getNom());
                                detail.put("note", n.getNote());
                                return detail;
                            })
                            .collect(Collectors.toList());

                    Map<String, Object> matiere = new LinkedHashMap<>();
                    matiere.put("idMatiere", nf.getIdMatiere());
                    matiere.put("nomMatiere", nf.getNomMatiere());
                    matiere.put("noteFinale", nf.getNoteFinale());
                    matiere.put("notesDetail", details);
                    return matiere;
                })
                .collect(Collectors.toList());

        double noteFinaleGlobale = notesFinales.stream()
                .mapToDouble(NoteFinale::getNoteFinale)
                .average()
                .orElse(0.0);
        noteFinaleGlobale = Math.round(noteFinaleGlobale * 100.0) / 100.0;

        Map<String, Object> resultat = new LinkedHashMap<>();
        resultat.put("idEleve", eleve.getId());
        resultat.put("nomEleve", eleve.getNom());
        resultat.put("noteFinalGlobale", noteFinaleGlobale);
        resultat.put("matieres", matieres);

        return resultat;
    }
}
