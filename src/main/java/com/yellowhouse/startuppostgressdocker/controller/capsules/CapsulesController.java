package com.yellowhouse.startuppostgressdocker.controller.capsules;

import com.yellowhouse.startuppostgressdocker.converter.CapsulesResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsulesResponse;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.repository.capsules.CapsulesRepository;
import com.yellowhouse.startuppostgressdocker.repository.clothes.ClothesRepository;
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
    public CapsulesRepository capsulesRepository;

    @Autowired
    public ClothesRepository clothesRepository;
    @Autowired
    public CapsulesService capsulesService;

    @Autowired
    public ClothesService clothesService;


    @PostMapping()
    public CapsulesResponse addCapsules(@RequestBody Capsules capsules) {
        capsulesService.createCapsule(capsules);
        return converter.convert(capsules);
    }

    @GetMapping()
    public List<CapsulesResponse> findAll() {
        List<CapsulesResponse> capsulesResponses = capsulesService.readAllCapsules().stream()
                .map(capsules -> converter.convert(capsules))
                .collect(Collectors.toList());
        return capsulesResponses;
    }


    @GetMapping("/{id}")
    public CapsulesResponse findCapsulesById(@PathVariable(value = "id") UUID capsulesId) {
        Capsules capsules = capsulesService.readCapsulesById(capsulesId);
        CapsulesResponse response = converter.convert(capsules);
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


    @GetMapping("/clothes")
    public void putClothesInCapsula(@RequestParam(value = "capsuleId") UUID capsuleId
            , @RequestParam(value = "clothesId") UUID clothesId) {
        Capsules capsules = capsulesService.readCapsulesById(capsuleId);
        Clothes clothes = clothesService.readClotheById(clothesId);
        if (!clothes.isInCapsula()) {
            clothes.setInCapsula(true);
            clothesService.update(clothes, clothesId);
        }
        capsules.addClothesToCapsule(clothes);
        capsulesService.update(capsules, capsuleId);
    }

}
