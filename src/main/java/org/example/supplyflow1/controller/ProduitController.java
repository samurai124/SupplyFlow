package org.example.supplyflow1.controller;

import org.example.supplyflow1.model.Fournisseur;
import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.service.FournisseurService;
import org.example.supplyflow1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public String listProduits(Model model) {
        List<Produit> produits = produitService.findAll();
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        model.addAttribute("produits", produits);
        model.addAttribute("fournisseurs", fournisseurs);
        return "produit";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Produit> getAllProduits() {
        return produitService.findAll().stream().map(e -> {
            Produit p = new Produit();
            p.setId(e.getId());
            p.setNom(e.getNom());
            p.setPrix(e.getPrix());
            p.setQuantite(e.getQuantite());
            p.setCategorie(e.getCategorie());
            return p;
        }).toList();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> saveProduit(
            @RequestParam String nom,
            @RequestParam String categorie,
            @RequestParam Double prix,
            @RequestParam Integer quantite,
            @RequestParam(required = false) Long fournisseur) {
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setCategorie(categorie);
        produit.setPrix(prix);
        produit.setQuantite(quantite);
        
        if (fournisseur != null) {
            produit.getFournisseurs().add(fournisseurService.findById(fournisseur));
        }
        
        produitService.save(produit);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduit(@PathVariable long id) {
        produitService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateProduit(@PathVariable long id, @RequestBody Produit produit) {
        produit.setId(id);
        produitService.save(produit);
        return ResponseEntity.ok().build();
    }
}
