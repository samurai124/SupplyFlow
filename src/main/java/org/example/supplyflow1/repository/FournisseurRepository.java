package org.example.supplyflow1.repository;

import org.example.supplyflow1.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur , Long> {
}
