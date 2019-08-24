package com.mproduits.web.controller;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductAlreadayExistException;
import com.mproduits.web.exceptions.ProductNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Api("API pour les opérations CRUD sur les produits.")
@RestController
public class ProductController {

	@Autowired
	ProductDao productDao;

	// Affiche la liste de tous les produits disponibles
	@ApiOperation(value = "Récupère tous les produit en stock!")
	@GetMapping(value = "/Produits")
	public List<Product> listeDesProduits() {

		List<Product> products = productDao.findAll();

		if (products.isEmpty())
			throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

		return products;

	}

	// Récuperer un produit par son id
	@ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
	@GetMapping(value = "/Produits/{id}")
	public Product recupererUnProduit(@PathVariable int id) {

		Product product = productDao.findById(id);

		if (product == null)
			throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

		return product;
	}

	// Ajouter un produit
	@PostMapping(value = "/Produits/add")
	public Product ajouterUnProduit(@Valid @RequestBody Product produit) {
		// if(produit == null) throw new ProductAlreadayExistException("Le produit
		// correspondant ne peut etre null ");
		if (produit == null || produit.getTitre().isEmpty() || produit.getTitre() == null)
			throw new ProductAlreadayExistException("Le produit correspondant ne peut etre null ");

		return productDao.save(produit);
	}

	@ApiOperation(value = "Modifier  un produit grâce à son ID à condition que celui-ci soit en stock!")
	@PutMapping(value = "/Produits")
	public void updateProduit(@RequestBody Product product) {

		productDao.save(product);
	}

	@ApiOperation(value = "Supprimer un produit grâce à son ID à condition que celui-ci soit en stock!")
	@DeleteMapping(value = "/Produits/{id}")
	public void supprimerProduit(@PathVariable int id) {

		productDao.deleteById(id);
	}

	@ApiOperation(value = "Récupère les produit dont le prix est inferieur au prix passé en parametre !")
	@GetMapping(value = "/Produits/prix/{prixLimit}")
	public List<Product> testeDeRequetes(@PathVariable double prixLimit) {

		return productDao.findByPrixGreaterThan(prixLimit);
	}

	@GetMapping(value = "/Produits/cher/{prixLimit}")
	public List<Product> produitpasCher(@PathVariable double prixLimit) {

		return productDao.chercherUnProduitCher(prixLimit);
	}

	@GetMapping(value = "/Produits/recherche/{recherche}")
	public List<Product> testeDeRequetes(@PathVariable String recherche) {
		return productDao.findByTitreLike("%" + recherche + "%");
	}

	// ajouter un produit
	@ApiOperation(value = "ajouter un produit grâce à son ID à condition que celui-ci soit en stock!")
	@PostMapping(value = "/Produits")
	public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

		Product productAdded = productDao.save(product);

		if (productAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productAdded.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//afficher la marge du produit
	@GetMapping(value="/AdminProduits")
	public List<String> calculerMargeProduit(){
		List<Product> products = productDao.findAll();

		if (products.isEmpty())
			throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");
		
		List<String> maliste= products.stream()
		.map(p->p + " : "+(p.getVente() - p.getPrix()) )
		.collect(Collectors.toList()); 

		return maliste;
		
		
	}
}
