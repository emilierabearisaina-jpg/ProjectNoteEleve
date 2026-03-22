package com.fourrage.eau.controller;

import com.fourrage.eau.model.Demande;
import com.fourrage.eau.repository.ClientRepository;
import com.fourrage.eau.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ClientRepository clientRepository;

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
        demandeRepository.save(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDemande(@PathVariable Long id) {
        demandeRepository.deleteById(id);
        return "redirect:/demandes";
    }
}
