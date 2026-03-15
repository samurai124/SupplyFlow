package org.example.supplyflow1.controller;

import org.example.supplyflow1.model.Fournisseur;
import org.example.supplyflow1.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {


    @Autowired
    private FournisseurService fournisseurService;



    @GetMapping
    public String listFournisseurs(Model model){
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        model.addAttribute("fournisseurs",fournisseurs);
        return "fournisseur";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> saveFournisseur(@RequestBody Fournisseur fournisseur){
        fournisseurService.save(fournisseur);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletFournisseur(@PathVariable long id){
        fournisseurService.deletById(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateFournisseur(@PathVariable long id, @RequestBody Fournisseur fournisseur){
        fournisseur.setId(id);
        fournisseurService.save(fournisseur);
        return ResponseEntity.ok().build();
    }


}
