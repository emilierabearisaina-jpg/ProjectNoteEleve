package com.fourrage.eau.controller;

import com.fourrage.eau.model.Demande;
import com.fourrage.eau.model.Devis;
import com.fourrage.eau.model.DevisDetail;
import com.fourrage.eau.model.TypeDevis;
import com.fourrage.eau.repository.DemandeRepository;
import com.fourrage.eau.repository.DevisRepository;
import com.fourrage.eau.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    @Autowired
    private DemandeRepository demandeRepository;

    @GetMapping("/new")
    public String showNewDevisForm(Model model) {
        model.addAttribute("typesDevis", typeDevisRepository.findAll());
        model.addAttribute("devis", new Devis());
        return "devis-form";
    }

    @GetMapping("/api/demande/{id}")
    @ResponseBody
    public ResponseEntity<?> getDemandeInfo(@PathVariable Long id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);
        if (demandeOpt.isPresent()) {
            Demande d = demandeOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", d.getId());
            response.put("clientNom", d.getClient() != null ? d.getClient().getNom() : "Inconnu");
            response.put("date", d.getDate() != null ? d.getDate().toString() : "");
            response.put("lieu", d.getLieu());
            response.put("district", d.getDistrict());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body("Demande non trouvée");
        }
    }

    @PostMapping("/save")
    public String saveDevis(@RequestParam Long demandeId,
                             @RequestParam Long typeDevisId,
                             @RequestParam LocalDate date,
                             @RequestParam String[] detailsLibelle,
                             @RequestParam Double[] detailsPu,
                             @RequestParam Double[] detailsQtt) {
        
        Devis devis = new Devis();
        devis.setDemande(demandeRepository.findById(demandeId).orElseThrow());
        devis.setTypeDevis(typeDevisRepository.findById(typeDevisId).orElseThrow());
        devis.setDate(date);
        
        double totalGlobal = 0;
        for (int i = 0; i < detailsLibelle.length; i++) {
            if (detailsLibelle[i] != null && !detailsLibelle[i].isEmpty()) {
                DevisDetail detail = new DevisDetail(detailsLibelle[i], detailsPu[i], detailsQtt[i], devis);
                devis.getDetails().add(detail);
            }
        }
        
        devisRepository.save(devis);
        
        return "redirect:/devis/new?success=true";
    }
}
