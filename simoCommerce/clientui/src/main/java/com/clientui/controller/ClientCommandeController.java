package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class ClientCommandeController {

    @Autowired
    private MicroserviceProduitsProxy produitsProxy;

    @Autowired
    private MicroserviceCommandeProxy commandesProxy;

    @Autowired
    private MicroservicePaiementProxy paiementsProxy;


    @RequestMapping(value="/commandes")
    public String afficherCommandes(Model model){

       List<CommandeBean> commandes =  commandesProxy.recupererLesCommande();

       model.addAttribute("commandes", commandes);

        return "Commandes";
    }
    
    
   
   
    /*
    * Étape (2)
    * Opération qui récupère les détails d'un produit
    * On passe l'objet "produit" récupéré et qui contient les détails en question à  FicheProduit.html
    * */
    @RequestMapping("/commandes/{idCommande}/details-produit/{id}")
    public String ficheProduit(@PathVariable int idCommande,@PathVariable int id,  Model model){
    	
    	CommandeBean commande =  commandesProxy.recupererUneCommande(idCommande);

        ProductBean produit = produitsProxy.recupererUnProduit(id);
        model.addAttribute("commande", commande);

        model.addAttribute("produit", produit);

        return "FicheCommande";
    }

   
    
}
