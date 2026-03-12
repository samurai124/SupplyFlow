package org.example.supplyflow1.controller;


import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.repository.ProduitRepository;
import org.example.supplyflow1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/produit")
public class ProduitController {


    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String listProduits(Model model){
        List<Produit> produits = produitService.findAll();
        model.addAttribute("produits",produits);
        return "produit";
    }


    @PostMapping
    public String saveProduit(
            @RequestParam String nom,
            @RequestParam String categorie,
            @RequestParam Double prix,
            @RequestParam Integer quantite
                              ){
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setCategorie(categorie);
        produit.setPrix(prix);
        produit.setQuantite(quantite);

        produitService.save(produit);
        return "redirect:/produit";

    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id){
        produitService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateProduit(@PathVariable long id, @RequestBody Produit produit){
        produit.setId(id);
        produitService.save(produit);
        return ResponseEntity.ok().build();
    }
}
