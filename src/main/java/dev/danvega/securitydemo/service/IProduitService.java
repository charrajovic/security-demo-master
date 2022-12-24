package dev.danvega.securitydemo.service;

import java.util.List;

import dev.danvega.securitydemo.entity.Produit;

public interface IProduitService {
	List<Produit> getProduits();
	
	void addProduit(Produit produit);
	void updateProduit(Produit produit);
	void deleteProduit(Long id);
}
