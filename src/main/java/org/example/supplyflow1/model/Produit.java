package org.example.supplyflow1.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    private String categorie;

    private double prix;

    private int quantite;

    @OneToMany(mappedBy = "produit" , cascade = CascadeType.ALL)
    private List<Mouvement> mouvements =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "produitFournisseur",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "fournisseur_id")
    )
    private List<Fournisseur> fournisseurs = new ArrayList<>();
    public Produit(){}

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
