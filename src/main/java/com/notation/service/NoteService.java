package com.notation.service;

import com.notation.entity.Eleve;
import com.notation.entity.Note;
import com.notation.entity.Parametre;
import com.notation.repository.EleveRepository;
import com.notation.repository.NoteRepository;
import com.notation.repository.ParametreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final EleveRepository eleveRepository;
    private final NoteRepository noteRepository;
    private final ParametreRepository parametreRepository;

    public Map<String, Object> getResultatEleve(Long idEleve) {
        Eleve eleve = eleveRepository.findById(idEleve)
                .orElseThrow(() -> new RuntimeException("Élève introuvable avec l'ID : " + idEleve));

        List<Note> notesDetail = noteRepository.findByIdEleveWithDetails(idEleve);
        Map<Long, List<Note>> notesByMatiere = notesDetail.stream()
                .collect(Collectors.groupingBy(Note::getIdMatiere));

        List<Map<String, Object>> matieresResult = new ArrayList<>();

        for (Map.Entry<Long, List<Note>> entry : notesByMatiere.entrySet()) {
            Long idMatiere = entry.getKey();
            List<Note> notes = entry.getValue();
            String nomMatiere = notes.get(0).getMatiere().getNom();

            double totalDiff = calculateTotalDiff(notes);
            List<Parametre> params = parametreRepository.findByMatiereId(idMatiere);
            
            double noteFinale = calculateNoteFinale(notes, totalDiff, params);

            List<Map<String, Object>> details = notes.stream()
                    .map(n -> {
                        Map<String, Object> detail = new LinkedHashMap<>();
                        detail.put("nomCorrecteur", n.getCorrecteur().getNom());
                        detail.put("note", n.getNote());
                        return detail;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> matiereMap = new LinkedHashMap<>();
            matiereMap.put("idMatiere", idMatiere);
            matiereMap.put("nomMatiere", nomMatiere);
            matiereMap.put("noteFinale", Math.round(noteFinale * 100.0) / 100.0);
            matiereMap.put("totalDiff", totalDiff); 
            matiereMap.put("notesDetail", details);
            matieresResult.add(matiereMap);
        }

        double noteFinaleGlobale = matieresResult.stream()
                .mapToDouble(m -> (Double) m.get("noteFinale"))
                .average()
                .orElse(0.0);
        noteFinaleGlobale = Math.round(noteFinaleGlobale * 100.0) / 100.0;

        Map<String, Object> resultat = new LinkedHashMap<>();
        resultat.put("idEleve", eleve.getId());
        resultat.put("nomEleve", eleve.getNom());
        resultat.put("noteFinalGlobale", noteFinaleGlobale);
        resultat.put("matieres", matieresResult);

        return resultat;
    }

    private double calculateTotalDiff(List<Note> notes) {
        double totalDiff = 0;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                totalDiff += Math.abs(notes.get(i).getNote() - notes.get(j).getNote());
            }
        }
        return totalDiff;
    }

    private double calculateNoteFinale(List<Note> notes, double totalDiff, List<Parametre> params) {
        if (notes.isEmpty()) return 0.0;
        if (params.isEmpty()) return notes.stream().mapToDouble(Note::getNote).average().orElse(0.0);

        Parametre pToUse = null;

        if (!params.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            
            for (Parametre p : params) {
                double currentDistance = Math.abs(totalDiff - p.getValeur());
                
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    pToUse = p;
                } else if (currentDistance == minDistance) {
                    if (pToUse != null && p.getValeur() < pToUse.getValeur()) {
                        pToUse = p;
                    }
                }
            }
        }

        if (pToUse != null) {
            boolean conditionMet = false;
            String op = pToUse.getOperateur().getNom();
            double val = pToUse.getValeur();

            switch (op) {
                case ">":  if (totalDiff > val)  conditionMet = true; break;
                case "<":  if (totalDiff < val)  conditionMet = true; break;
                case ">=": if (totalDiff >= val) conditionMet = true; break;
                case "<=": if (totalDiff <= val) conditionMet = true; break;
            }

            if (conditionMet) {
                String res = pToUse.getResolution().getNom();
                if (res.equalsIgnoreCase("Petit") || res.equalsIgnoreCase("plus petit")) {
                    return notes.stream().mapToDouble(Note::getNote).min().orElse(0.0);
                } else if (res.equalsIgnoreCase("Grand") || res.equalsIgnoreCase("plus grand")) {
                    return notes.stream().mapToDouble(Note::getNote).max().orElse(0.0);
                } else if (res.equalsIgnoreCase("Moyenne")) {
                    return notes.stream().mapToDouble(Note::getNote).average().orElse(0.0);
                }
            }
        }

        return notes.stream().mapToDouble(Note::getNote).average().orElse(0.0);
    }
}
