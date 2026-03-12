package org.example.supplyflow1.service;


import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Transactional
    public void save(Produit produit){
        produitRepository.save(produit);
    }


    @Transactional
    public List<Produit> findAll(){
        return produitRepository.findAll();
    }


    @Transactional
    public Produit findById(long id){
        return produitRepository.findById(id).get();
    }


    @Transactional
    public void delete(long id){
        produitRepository.deleteById(id);
    }


    @Transactional
    public void deletAll(){
        produitRepository.deleteAll();
    }
}
