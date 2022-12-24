package dev.danvega.securitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danvega.securitydemo.entity.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
