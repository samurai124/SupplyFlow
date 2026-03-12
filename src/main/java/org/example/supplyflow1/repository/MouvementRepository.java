package org.example.supplyflow1.repository;

import org.example.supplyflow1.model.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepository extends JpaRepository<Mouvement , Long> {
}
