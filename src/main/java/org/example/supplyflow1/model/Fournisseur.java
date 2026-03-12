package org.example.supplyflow1.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fourinsseurs")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    private String ville;

    private String telephone;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits){
        this.produits = produits;
    }

    @ManyToMany(mappedBy = "fournisseurs")
    private List<Produit> produits;
    public Fournisseur(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
