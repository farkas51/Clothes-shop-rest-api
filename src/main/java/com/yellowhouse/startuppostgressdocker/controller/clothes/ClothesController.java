package com.yellowhouse.startuppostgressdocker.controller.clothes;

import com.yellowhouse.startuppostgressdocker.converter.ClothesResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.model.clothes.ClothesResponse;
import com.yellowhouse.startuppostgressdocker.service.clothes.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clothes")
public class ClothesController {

    @Autowired
    public ClothesResponseConverter converter;
    @Autowired
    public ClothesService clothesService;

    @PostMapping
    public ClothesResponse addClothes(@RequestBody Clothes clothes) {
        clothesService.createClothes(clothes);
        ClothesResponse clothesResponse = converter.convert(clothes);
        return clothesResponse;
    }

    @GetMapping
    public List<ClothesResponse> findAll() {
        List<ClothesResponse> clothesList = clothesService.readAllClothes().stream()
                .map(clothes -> converter.convert(clothes))
                .collect(Collectors.toList());
        return clothesList;
    }

    @GetMapping("/{id}")
    public ClothesResponse findClothesById(@PathVariable(value = "id") UUID clothesId) {
        Clothes clothes = clothesService.readClotheById(clothesId);
        ClothesResponse response = converter.convert(clothes);
        return response;
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

    @GetMapping("/clothes-in-status/{status}")
    public List<ClothesResponse> findClothesInStatus(@PathVariable(value = "status") Integer status) {
        List<ClothesResponse> clothesList = clothesService.findByStatus(status).stream()
                .map(clothes -> converter.convert(clothes))
                .collect(Collectors.toList());
        return clothesList;
    }

    @GetMapping("/clothes-in-capsula")
    public List<ClothesResponse> findClothesInCapsula(@RequestParam(value = "capsuleId") UUID capsuleId) {
        List<ClothesResponse> clothesList = clothesService.getClothesWhereCapsules(capsuleId).stream()
                .map(clothes -> converter.convert(clothes))
                .collect(Collectors.toList());
        return clothesList;
    }

    @PatchMapping("/{id}")
    public Clothes patchClothes(@PathVariable(value = "id") UUID id, @RequestBody Map<Object, Object> fields) {
        Clothes patchedClothes = clothesService.patch(id, fields);
        return patchedClothes;
    }
}
