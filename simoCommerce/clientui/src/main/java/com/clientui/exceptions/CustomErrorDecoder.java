package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;


public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {

        if(reponse.status() == 400 ) {
            return new ProductBadRequestException(
                    "Requête incorrecte survient dans microservice produit " + reponse.body().toString()
            );
            
        }
        else if (reponse.status() == 404 ) {
            return new ProductNotFoundException(
                    "Produit non trouvé "
            );
        }
        else if(reponse.status() > 404 && reponse.status() <=499 ) {
            return new Product4XXException(
                    "Erreur de au format 4XX " + reponse.body().toString()
            );
        }
        

        return defaultErrorDecoder.decode(invoqueur, reponse);
    }

}
