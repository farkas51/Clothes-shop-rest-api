package com.yellowhouse.startuppostgressdocker.controller.clothes;

import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.service.clothes.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clothes")
public class ClothesController {

    @Autowired
    public ClothesService clothesService;

    @PostMapping
    public ResponseEntity<Clothes> addClothes(@RequestBody Clothes clothes) {
        clothesService.createClothes(clothes);
        return ResponseEntity.ok().body(clothes);
    }

    @GetMapping
    public ResponseEntity<List<Clothes>> findAll() {
        return ResponseEntity.ok(clothesService.readAllClothes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clothes> findClothesById(@PathVariable(value = "id") UUID clothesId) {
        Clothes clothes = clothesService.readClotheById(clothesId);
        return ResponseEntity.ok().body(clothes);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothes(@PathVariable(value = "id") UUID clothesId) {
        boolean flag = false;
        if (clothesService.deleteClothesById(clothesId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
