package com.mproduits.service;

import com.mproduits.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface ProductService {


    List<Product> listeDesProduits();

    Product recupererUnProduit(int id);

    Product ajouterUnProduit(Product produit);

    void supprimerProduit(int id);

    List<Product> testeDeRequetes(double prixLimit);
    List<Product> testeRecherche(String recherche);

    List<Product> produitpasCher(double prixLimit);

    Product ajouterProduit(Product product);

    List<Product> calculerMargeProduit();
}
