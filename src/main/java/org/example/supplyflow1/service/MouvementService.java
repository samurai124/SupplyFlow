package org.example.supplyflow1.service;


import org.example.supplyflow1.model.Mouvement;
import org.example.supplyflow1.model.Produit;
import org.example.supplyflow1.repository.MouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MouvementService {

    @Autowired
    private  MouvementRepository mouvementRepository;

    @Transactional
    public void save(Mouvement mouvement){
        mouvementRepository.save(mouvement);
    }

    @Transactional
    public List<Mouvement> findAll(){
        return mouvementRepository.findAll();
    }

    @Transactional
    public Mouvement findById(long id){
        return mouvementRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(long id){
        mouvementRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(){
        mouvementRepository.deleteAll();
    }

}
