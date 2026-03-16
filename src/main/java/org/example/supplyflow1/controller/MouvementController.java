package org.example.supplyflow1.controller;


import org.example.supplyflow1.model.Mouvement;
import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.service.MouvementService;
import org.example.supplyflow1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        List<Produit> produits = produitService.findAll();
        model.addAttribute("mouvements",mouvements);
        model.addAttribute("produits",produits);

        return "mouvement";
    }

    @PostMapping("/{produit_id}")
    @ResponseBody
    public  ResponseEntity<Void>  seveMouvement(
            @PathVariable long produit_id,
            @RequestBody Mouvement mouvement
            ){
        Produit produit = produitService.findById(produit_id);
        if("entrer".equals(mouvement.getType())){
            produit.setQuantite(produit.getQuantite() + mouvement.getQuantite());
        }
        if ("sortie".equals(mouvement.getType())){
            produit.setQuantite(produit.getQuantite() - mouvement.getQuantite());
        }
        produitService.save(produit);
        mouvement.setProduit(produitService.findById(produit_id));
        mouvement.setDate(LocalDateTime.now());
        mouvementService.save(mouvement);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> editMouvement(
            @PathVariable long id,
            @RequestBody Mouvement mouvement
    ){
       Mouvement m = mouvementService.findById(id);
       mouvement.setId(id);
       mouvement.setDate(m.getDate());
       if ("entrer".equals(m.getType()) && "sortie".equals(mouvement.getType())){
           Produit produit = m.getProduit();
           produit.setQuantite(produit.getQuantite() - m.getQuantite());
           produit.setQuantite(produit.getQuantite() - mouvement.getQuantite());
           produitService.save(produit);
           mouvementService.save(mouvement);
       }
       else if("sortie".equals(m.getType()) && "entrer".equals(mouvement.getType())){
           Produit produit = m.getProduit();
           produit.setQuantite(produit.getQuantite() + m.getQuantite());
           produit.setQuantite(produit.getQuantite() + mouvement.getQuantite());
           produitService.save(produit);
           mouvementService.save(mouvement);
       }
       else if (m.getType().equals(mouvement.getType()) && m.getProduit().getId() == mouvement.getProduit().getId()){
           Produit produit = m.getProduit();
           if ("entrer".equals(mouvement.getType())){
               produit.setQuantite(produit.getQuantite() + mouvement.getQuantite());
           }
           if ("sortie".equals(mouvement.getType())){
               produit.setQuantite(produit.getQuantite() - mouvement.getQuantite());
           }
           mouvementService.save(mouvement);
       }
       else if (m.getType().equals(mouvement.getType()) && m.getProduit().getId() != mouvement.getProduit().getId()){
           Produit oldProduit = m.getProduit();
           Produit newProduit = mouvement.getProduit();

           if ("entrer".equals(mouvement.getType())){
               oldProduit.setQuantite(oldProduit.getQuantite() - m.getQuantite());
           } else {
               oldProduit.setQuantite(oldProduit.getQuantite() + m.getQuantite());
           }

           if ("entrer".equals(mouvement.getType())){
               newProduit.setQuantite(newProduit.getQuantite() + mouvement.getQuantite());
           } else {
               newProduit.setQuantite(newProduit.getQuantite() - mouvement.getQuantite());
           }

           produitService.save(oldProduit);
           produitService.save(newProduit);
           mouvementService.save(mouvement);
       }


        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteMouvement(
            @PathVariable long id
    ){
        mouvementService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
