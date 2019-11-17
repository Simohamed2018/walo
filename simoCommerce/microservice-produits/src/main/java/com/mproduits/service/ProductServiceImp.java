package com.mproduits.service;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class ProductServiceImp  implements  ProductService{

    @Autowired
    ProductDao productDao;

    // Affiche la liste de tous les produits disponibles
    public List<Product> listeDesProduits() {

        List<Product> products = productDao.findAll();
        return products;

    }

    // Récuperer un produit par son id

    public Product recupererUnProduit(int id) {

        Product product = productDao.findById(id);
     return product;
    }

    // Ajouter un produit

    public Product ajouterUnProduit( Product produit) {


        return productDao.save(produit);
    }


    public void updateProduit( Product product) {

        productDao.save(product);
    }


    public void supprimerProduit(int id) {

        productDao.deleteById(id);
    }


    public List<Product> testeDeRequetes(double prixLimit) {

        return productDao.findByPrixGreaterThan(prixLimit);
    }


    public List<Product> produitpasCher(double prixLimit) {

        return productDao.chercherUnProduitCher(prixLimit);
    }


    public List<Product> testeRecherche (String recherche) {
        return productDao.findByTitreLike("%" + recherche + "%");
    }

    // ajouter un produit

    public Product ajouterProduit(Product product) {

        Product productAdded = productDao.save(product);

        return productAdded;
    }


    public List<Product> calculerMargeProduit(){
        List<Product> products = productDao.findAll();

        return products;

    }
}


