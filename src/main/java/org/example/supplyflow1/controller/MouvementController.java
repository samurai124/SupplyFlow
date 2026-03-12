package org.example.supplyflow1.controller;


import org.example.supplyflow1.model.Mouvement;
import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.service.MouvementService;
import org.example.supplyflow1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/mouvement")
public class MouvementController {

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private ProduitService produitService;

    @GetMapping
    private String listMouvements(Model model){
        List<Mouvement> mouvements = mouvementService.findAll();
        model.addAttribute("mouvements",mouvements);
        return "mouvement";
    }

    @PostMapping
    public String seveMouvement(
            @RequestParam String type,
            @RequestParam LocalDateTime date,
            @RequestParam int quantite,
            @RequestParam long produit_id
            ){
        Produit produit = produitService.findById(produit_id);
        if("in".equals(type)){
            produit.setQuantite(produit.getQuantite() + quantite);
        }
        if ("out".equals(type)){
            produit.setQuantite(produit.getQuantite() - quantite);
        }
        Mouvement mouvement = new Mouvement();
        mouvement.setType(type);
        mouvement.setDate(date);
        mouvement.setQuantite(quantite);
        mouvement.setProduit(produit);
        mouvementService.save(mouvement);
        return "redirect:/mouvement";
    }




}
