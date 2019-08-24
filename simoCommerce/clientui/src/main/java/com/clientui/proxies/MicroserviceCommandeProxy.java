package com.clientui.proxies;

import com.clientui.beans.CommandeBean;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-commandes", url = "localhost:9002")
public interface MicroserviceCommandeProxy {

    @PostMapping(value = "/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
    
    @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable int id);
    
    @GetMapping(value = "/commandes")
    public List<CommandeBean> recupererLesCommande();
}
