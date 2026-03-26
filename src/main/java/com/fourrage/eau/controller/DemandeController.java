package com.fourrage.eau.controller;

import com.fourrage.eau.model.Demande;
import com.fourrage.eau.model.DemandeStatut;
import com.fourrage.eau.model.Status;
import com.fourrage.eau.repository.ClientRepository;
import com.fourrage.eau.repository.DemandeRepository;
import com.fourrage.eau.repository.DemandeStatutRepository;
import com.fourrage.eau.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/demandes")
@Transactional
public class DemandeController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private DemandeStatutRepository demandeStatutRepository;

    @GetMapping
    public String showDemandesPage(Model model) {
        model.addAttribute("demandes", demandeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("newDemande", new Demande());
        return "demandes";
    }

    @PostMapping("/add")
    public String addDemande(@ModelAttribute Demande demande) {
        if(demande.getDate() == null) {
            demande.setDate(LocalDate.now()); 
        }

        Demande savedDemande = demandeRepository.save(demande);

        Status status = statusRepository.findByLibelle("crée")
                        .orElseGet(() -> statusRepository.findByLibelle("Crée")
                        .orElseGet(() -> {
                            Status newStatus = new Status("crée");
                            return statusRepository.save(newStatus);
                        }));

        DemandeStatut demandeStatut = new DemandeStatut(LocalDate.now(), status, savedDemande);
        demandeStatutRepository.save(demandeStatut);
        
        if (savedDemande.getStatuts() == null) {
            savedDemande.setStatuts(new java.util.ArrayList<>());
        }
        savedDemande.getStatuts().add(demandeStatut);

        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDemande(@PathVariable Long id) {
        demandeRepository.deleteById(id);
        return "redirect:/demandes";
    }
}
