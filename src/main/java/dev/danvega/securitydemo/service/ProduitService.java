package dev.danvega.securitydemo.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.danvega.securitydemo.entity.Produit;
import dev.danvega.securitydemo.repository.ProduitRepository;

@Service
@Primary
public class ProduitService implements ICrudService<Produit, Long>{

	@Autowired
	private ProduitRepository produitRepository;
	
	@Override
	public List<Produit> getAll() {
		return produitRepository.findAll();
	}

	@Override
	public void add(Produit produit) {
		produitRepository.save(produit);
	}

	@Override
	public void update(Produit produit) {
		produitRepository.save(produit);
	}

	@Override
	public void delete(Long id) {
		Produit produit = new Produit();
		produit.setId(id);
		produitRepository.delete(produit);
	}

}