package com.mproduits.web.controller;


import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @Autowired
    ProductDao productDao;


    @GetMapping(value="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
        Product p=productDao.findById(id);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/mcommerce/products/"+p.getImage()));
    }
    @PostMapping(value = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable int id) throws Exception{
        Product p=productDao.findById(id);
        p.setImage(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/mcommerce/products/"+p.getImage()),file.getBytes());
        productDao.save(p);

    }
}
