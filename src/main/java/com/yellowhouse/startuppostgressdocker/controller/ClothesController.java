package com.yellowhouse.startuppostgressdocker.controller;

import com.yellowhouse.startuppostgressdocker.model.Clothes;
import com.yellowhouse.startuppostgressdocker.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClothesController {

    @Autowired
    public ClothesRepository clothesRepository;

    @PostMapping(value = "/clothes")
    public Clothes addClothes(@RequestBody Clothes clothes){
        return clothesRepository.save(clothes);
    }

    @GetMapping(value = "/clothes")
    public ResponseEntity<List<Clothes>> findAll(){
       return ResponseEntity.ok(clothesRepository.findAll());
    }


    @GetMapping("/clothes/{id}")
    public ResponseEntity<Clothes> findClothesById(@PathVariable(value = "id") UUID clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId).orElseThrow(
                () -> new ResouceNotFoundException("Clothes not found" + clothesId));
        return ResponseEntity.ok().body(clothes);
    }


    @DeleteMapping("/clothes/{id}")
    public ResponseEntity<Void> deleteClothes(@PathVariable(value = "id") UUID clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found" + clothesId));
        clothesRepository.delete(clothes);
        return ResponseEntity.ok().build();
    }

}
