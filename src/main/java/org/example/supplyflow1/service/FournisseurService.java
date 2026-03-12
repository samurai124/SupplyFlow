package org.example.supplyflow1.service;


import org.example.supplyflow1.model.Fournisseur;
import org.example.supplyflow1.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;


    @Transactional
    public void save(Fournisseur fournisseur){
        fournisseurRepository.save(fournisseur);
    }

    @Transactional
    public List<Fournisseur> findAll(){
        return fournisseurRepository.findAll();
    }

    @Transactional
    public Fournisseur findById(long id){
        return fournisseurRepository.findById(id).get();
    }

    @Transactional
    public void deletById(long id){
        fournisseurRepository.deleteById(id);
    }


    @Transactional
    public void deletAll(){
        fournisseurRepository.deleteAll();
    }

}
