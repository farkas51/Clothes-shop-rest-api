package com.yellowhouse.startuppostgressdocker.service.clothes;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.repository.clothes.ClothesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public void update(Clothes clothes, UUID clothesId) {
        try {
            clothesRepository.findById(clothesId);
            clothesRepository.save(clothes);
        } catch (Exception e) {
            log.info("Вещь не найдена");
            throw new ResourceNotFoundException("Clothes not found" + clothesId);
        }
    }

    @Override
    public List<Clothes> findByStatus(Integer status) {
        log.info("Найдена вещь по статусу");
        return clothesRepository.getByStatus(status);
    }

    @Override
    public List<Clothes> getClothesWhereCapsules(UUID capsuleId) {
        List<Clothes> clothes = clothesRepository.findByCapsules_id(capsuleId);
        clothes.size();
        log.info("Получен список вещей которые входят в определённую капсулу");
        return clothes;
    }

    @Override
    public Clothes patch(UUID clothesId, Map<Object, Object> fields) {
        Optional<Clothes> clothes = clothesRepository.findById(clothesId);
        if (clothes.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Clothes.class, key.toString());
                field.setAccessible(true);
                if (field.getType().equals(UUID.class)) {
                    ReflectionUtils.setField(field, clothes.get(), UUID.fromString(value.toString()));
                } else if (field.getType().equals(int.class)) {
                    ReflectionUtils.setField(field, clothes.get(), Integer.valueOf(value.toString()));
                } else if (field.getType().equals(LocalDateTime.class)) {
                    ReflectionUtils.setField(field, clothes.get(), LocalDateTime.parse(value.toString()));

                } else {
                    ReflectionUtils.setField(field, clothes.get(), value.toString());
                }
            });
            Clothes updatedClothes = clothesRepository.save(clothes.get());
            log.info("Вещь обновлена");
            return updatedClothes;
        } else {
            throw new ResourceNotFoundException("Запись с вещью по заданному id не найдена");
        }
    }


}
