package com.yellowhouse.startuppostgressdocker.service;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.Capsules;
import com.yellowhouse.startuppostgressdocker.model.Clothes;
import com.yellowhouse.startuppostgressdocker.repository.CapsulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CapsulesServiceImpl implements CapsulesService{

    @Autowired
    public CapsulesRepository capsulesRepository;

    @Override
    public void createCapsule(Capsules capsule) {
        capsulesRepository.save(capsule);
        log.info("Создана запись о капсуле ");
    }

    @Override
    public List<Capsules> readAllCapsules() {
        List<Capsules> capsulesList = capsulesRepository.findAll();
        log.info("Возвращены все капсулы ");
        return new ArrayList<>(capsulesList);
    }

    @Override
    public Capsules readCapsulesById(UUID capsuleId) {
        Capsules capsules = capsulesRepository.findById(capsuleId).orElseThrow(
                () -> new ResourceNotFoundException("Capsules not found " + capsuleId));
        log.info("Получена капсула " + capsuleId);
        return capsules;
    }

    @Override
    public boolean deleteClothesById(UUID capsuleId) throws ResourceNotFoundException{
        boolean flag = false;
        try {
            capsulesRepository.deleteById(capsuleId);
        } catch (Exception e) {
            log.info("Капсула не найдена");
            throw new ResourceNotFoundException("Capsule not found" + capsuleId);
        }

        log.info("Удалена капсула " + capsuleId);
        return  flag = true;
    }
}
