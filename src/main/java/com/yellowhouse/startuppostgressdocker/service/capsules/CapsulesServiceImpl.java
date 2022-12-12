package com.yellowhouse.startuppostgressdocker.service.capsules;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.converter.CapsulesResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.repository.capsules.CapsulesRepository;
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
public class CapsulesServiceImpl implements CapsulesService {

    @Autowired
    public CapsulesResponseConverter converter;

    @Autowired
    public CapsulesRepository capsulesRepository;

    @Autowired
    public ClothesRepository clothesRepository;

    @Override
    public void createCapsule(Capsule capsule) {
        capsulesRepository.save(capsule);
        log.info("Создана запись о капсуле ");
    }

    @Override
    public List<Capsule> readAllCapsules() {
        List<Capsule> capsuleList = capsulesRepository.findAll();
        log.info("Возвращены все капсулы ");
        return new ArrayList<>(capsuleList);
    }

    @Override
    public Capsule readCapsulesById(UUID capsuleId) {
        Capsule capsule = capsulesRepository.findById(capsuleId).orElseThrow(
                () -> new ResourceNotFoundException("Capsules not found " + capsuleId));
        log.info("Получена капсула " + capsuleId);
        return capsule;
    }

    @Override
    public boolean deleteClothesById(UUID capsuleId) throws ResourceNotFoundException {
        boolean flag = false;
        try {
            capsulesRepository.deleteById(capsuleId);
        } catch (Exception e) {
            log.info("Капсула не найдена");
            throw new ResourceNotFoundException("Capsule not found" + capsuleId);
        }

        log.info("Удалена капсула " + capsuleId);
        return flag = true;
    }

    @Override
    public void update(Capsule capsule, UUID id) {
        try {
            capsulesRepository.findById(id);
            capsulesRepository.save(capsule);
        } catch (Exception e) {
            log.info("Капсула не найдена");
            throw new ResourceNotFoundException("Capsules not found" + id);
        }
    }


    @Override
    public List<Capsule> getCapsulesWhereClothes(UUID clothesId) {
        List<Capsule> capsules = capsulesRepository.findByClothesInCapsula_id(clothesId);
        log.info("Получена капсула где находится одежда");
        return capsules;
    }

    @Override
    public Set<String> getSizesInCapsulaByTypeAndStyle(String size, String type) {
        List<Capsule> capsules = capsulesRepository.getBySizeAndType(Integer.parseInt(size), type);

        if (capsules.isEmpty()) {
            throw new ResourceNotFoundException("Капсулы с данными параметрами не найдены");
        }

        Set<String> sizes = new HashSet<>();
        for (Capsule capsule : capsules
        ) {
            if (capsule.getStatus().equals("NEW") || capsule.getStatus().equals("IN_STOCK"))
                sizes.add(capsule.getClothesSize());
        }
        log.info("Получены размеры в капсулах по стилю и размеру капсулы");
        return sizes;
    }

    @Override
    public Capsule getRandomCapsula(String size, String type, String clothesSize) throws ResourceNotFoundException {
        List<Capsule> capsulesList = capsulesRepository.getBySizeAndTypeAndClothesSize(Integer.parseInt(size), type, clothesSize);

        if (capsulesList.isEmpty()) {
            throw new ResourceNotFoundException("Капсулы с данными параметрами не найдены");
        }

        List<Capsule> capsulesInValidStatus = new ArrayList<>();
        for (Capsule capsule : capsulesList
        ) {
            if (capsule.getStatus().equals("NEW") || capsule.getStatus().equals("IN_STOCK"))
                capsulesInValidStatus.add(capsule);
        }

        if (capsulesInValidStatus.isEmpty()) {
            throw new ResourceNotFoundException("Нет капсул в статусе NEW или IN_STOCK");
        }

        Random r = new Random();

        int i = r.nextInt(capsulesInValidStatus.size());
        Capsule randomCapsule = capsulesInValidStatus.get(i);
        randomCapsule.setStatus("ON_CLIENT");
        capsulesRepository.save(randomCapsule);
        return randomCapsule;
    }

    @Override
    public Capsule patch(UUID capsuleId, Map<Object, Object> fields) {
        Optional<Capsule> capsule = capsulesRepository.findById(capsuleId);
        if (capsule.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Capsule.class, key.toString());
                field.setAccessible(true);
                if (field.getType().equals(UUID.class)) {
                    ReflectionUtils.setField(field, capsule.get(), UUID.fromString(value.toString()));
                } else if (field.getType().equals(int.class)) {
                    ReflectionUtils.setField(field, capsule.get(), Integer.valueOf(value.toString()));
                } else if (field.getType().equals(LocalDateTime.class)) {
                    ReflectionUtils.setField(field, capsule.get(), LocalDateTime.parse(value.toString()));

                } else {
                    ReflectionUtils.setField(field, capsule.get(), value.toString());
                }
            });
            Capsule updatedCapsule = capsulesRepository.save(capsule.get());
            log.info("Капсула обновлена");
            return updatedCapsule;
        } else {
            throw new ResourceNotFoundException("Запись с капсулой по заданному id не найдена");
        }
    }

}
