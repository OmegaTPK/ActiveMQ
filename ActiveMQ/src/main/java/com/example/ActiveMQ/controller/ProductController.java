package com.example.ActiveMQ.controller;

import com.example.ActiveMQ.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api"})
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/migrate")
    public ResponseEntity<String> migrateProducts(){
        productService.sendingProductsInQueue();
        return ResponseEntity.ok("Migration started");
    }

    @PostMapping(path = "/generate/{number}")
    public String generateProducts(@PathVariable("number") Long count){
        productService.generate(count);
        return "ok";
    }
}
