package org.example.supplyflow1.repository;

import org.example.supplyflow1.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit , Long> {
}
