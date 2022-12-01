package com.yellowhouse.startuppostgressdocker.controller.capsules;

import com.yellowhouse.startuppostgressdocker.converter.CapsulesResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsuleResponse;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.service.capsules.CapsulesService;
import com.yellowhouse.startuppostgressdocker.service.clothes.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/capsules")
public class CapsulesController {

    @Autowired
    public CapsulesResponseConverter converter;
    @Autowired
    public CapsulesService capsulesService;

    @Autowired
    public ClothesService clothesService;


    @PostMapping()
    public CapsuleResponse addCapsules(@RequestBody Capsule capsule) {
        capsulesService.createCapsule(capsule);
        return converter.convert(capsule);
    }

    @GetMapping()
    public List<CapsuleResponse> findAll() {
        List<CapsuleResponse> capsuleRespons = capsulesService.readAllCapsules().stream()
                .map(capsules -> converter.convert(capsules))
                .collect(Collectors.toList());
        return capsuleRespons;
    }


    @GetMapping("/{id}")
    public CapsuleResponse findCapsulesById(@PathVariable(value = "id") UUID capsulesId) {
        Capsule capsule = capsulesService.readCapsulesById(capsulesId);
        CapsuleResponse response = converter.convert(capsule);
        return response;
    }


    @DeleteMapping()
    public ResponseEntity<Void> deleteCapsules(@PathVariable(value = "id") UUID capsulesId) {
        if (capsulesService.deleteClothesById(capsulesId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/add-clothes-to-capsule")
    public void putClothesInCapsula(@RequestParam(value = "capsuleId") UUID capsuleId
            , @RequestParam(value = "clothesId") UUID clothesId) {
        Capsule capsule = capsulesService.readCapsulesById(capsuleId);
        Clothes clothes = clothesService.readClotheById(clothesId);
        if (!clothes.isInCapsula()) {
            clothes.setInCapsula(true);
            clothesService.update(clothes, clothesId);
        }
        capsule.addClothesToCapsule(clothes);
        capsulesService.update(capsule, capsuleId);
    }

    @GetMapping("/capsules-with-clothes")
    public List<CapsuleResponse> findCapsulesWhereClothes(@RequestParam(value = "clothesId") UUID clothesId) {
        List<CapsuleResponse> capsuleResponse = capsulesService.getCapsulesWhereClothes(clothesId).stream()
                .map(capsules -> converter.convert(capsules))
                .collect(Collectors.toList());
        return capsuleResponse;
    }
}
