package com.yellowhouse.startuppostgressdocker.service.capsules;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.converter.ClothesResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.model.clothes.ClothesResponse;
import com.yellowhouse.startuppostgressdocker.repository.capsules.CapsulesRepository;
import com.yellowhouse.startuppostgressdocker.repository.clothes.ClothesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CapsulesServiceImpl implements CapsulesService {

    @Autowired
    public ClothesResponseConverter converter;

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
       return capsules;
    }

}
