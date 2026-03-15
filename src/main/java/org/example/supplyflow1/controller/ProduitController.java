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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/produit")
public class ProduitController {


    @Autowired
    private ProduitService produitService;

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public String listProduits(Model model){
        List<Produit> produits = produitService.findAll();
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        model.addAttribute("produits",produits);
        model.addAttribute("fournisseurs",fournisseurs);
        return "produit";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Produit> getAllProduits() {
        return produitService.findAll()
                .stream()
                .map(e -> {
                    Produit p = new Produit();
                    p.setId(e.getId());
                    p.setNom(e.getNom());
                    p.setPrix(e.getPrix());
                    p.setQuantite(e.getQuantite());
                    p.setCategorie(e.getCategorie());
                    return p;
                })
                .toList();
    }


    @PostMapping
    public String saveProduit(
            @RequestParam String nom,
            @RequestParam String categorie,
            @RequestParam Double prix,
            @RequestParam Integer quantite,
            @RequestParam long fournisseur
                              ){
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setCategorie(categorie);
        produit.setPrix(prix);
        produit.setQuantite(quantite);
        produit.getFournisseurs().add(fournisseurService.findById(fournisseur));

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
    public ResponseEntity<Void> updateProduit(@PathVariable long id, @RequestBody ProduitUpdateRequest request){
        Produit existingProduit;
        try {
            existingProduit = produitService.findById(id);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }

        if (request.getNom() == null || request.getCategorie() == null || request.getPrix() == null || request.getQuantite() == null) {
            return ResponseEntity.badRequest().build();
        }

        existingProduit.setNom(request.getNom());
        existingProduit.setCategorie(request.getCategorie());
        existingProduit.setPrix(request.getPrix());
        existingProduit.setQuantite(request.getQuantite());

        if (request.getFournisseur() != null) {
            try {
                Fournisseur fournisseur = fournisseurService.findById(request.getFournisseur());
                List<Fournisseur> fournisseurs = new ArrayList<>();
                fournisseurs.add(fournisseur);
                existingProduit.setFournisseurs(fournisseurs);
            } catch (NoSuchElementException ex) {
                return ResponseEntity.badRequest().build();
            }
        }

        produitService.save(existingProduit);
        return ResponseEntity.ok().build();
    }

    public static class ProduitUpdateRequest {
        private String nom;
        private String categorie;
        private Double prix;
        private Integer quantite;
        private Long fournisseur;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCategorie() {
            return categorie;
        }

        public void setCategorie(String categorie) {
            this.categorie = categorie;
        }

        public Double getPrix() {
            return prix;
        }

        public void setPrix(Double prix) {
            this.prix = prix;
        }

        public Integer getQuantite() {
            return quantite;
        }

        public void setQuantite(Integer quantite) {
            this.quantite = quantite;
        }

        public Long getFournisseur() {
            return fournisseur;
        }

        public void setFournisseur(Long fournisseur) {
            this.fournisseur = fournisseur;
        }
    }
}
