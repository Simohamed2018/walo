package com.mproduits.dao;

import com.mproduits.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	    Product findById(int id);
	    
	    List<Product> findByPrixGreaterThan(double prixLimit);
	    
	    List<Product> findByTitreLike(String recherche);
	    
	    @Query("SELECT id, titre, prix FROM Product p WHERE p.prix > :prixLimit")
	    List<Product>  chercherUnProduitCher(@Param("prixLimit") double prixLimit);
	}

