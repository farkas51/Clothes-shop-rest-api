package com.yellowhouse.startuppostgressdocker.service.clothes;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.repository.clothes.ClothesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ClothesServiceImpl implements ClothesService {

    @Autowired
    public ClothesRepository clothesRepository;

    @Override
    public void createClothes(Clothes clothes) {
        clothesRepository.save(clothes);
        log.info("Создана запись о вещи ");
    }

    @Override
    public List<Clothes> readAllClothes() {
        List<Clothes> clothesList = clothesRepository.findAll();
        log.info("Возвращены все вещи ");
        return new ArrayList<>(clothesList);
    }

    @Override
    public Clothes readClotheById(UUID clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId).orElseThrow(
                () -> new ResourceNotFoundException("Clothes not found " + clothesId));
        log.info("Получена вещь " + clothesId);
        return clothes;
    }

    @Override
    public boolean deleteClothesById(UUID clothesId) throws ResourceNotFoundException {
        boolean flag = false;
        try {
            clothesRepository.deleteById(clothesId);
        } catch (Exception e) {
            log.info("Вещь не найдена");
            throw new ResourceNotFoundException("Clothes not found" + clothesId);
        }

        log.info("Удалена вещь " + clothesId);
        return flag = true;
    }
//
//
//    @Override
//    public boolean update(Clothes clothes, UUID id) {
//        if (CLOTHES_REPOSITORY_MAP.containsKey(id)) {
//            clothes.setId(id);
//            CLOTHES_REPOSITORY_MAP.put(id, clothes);
//            return true;
//        }
//
//        return false;
//    }
//

}
