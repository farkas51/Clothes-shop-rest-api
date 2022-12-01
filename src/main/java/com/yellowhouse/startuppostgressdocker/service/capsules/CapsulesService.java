package com.yellowhouse.startuppostgressdocker.service.capsules;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;

import java.util.List;
import java.util.UUID;

public interface CapsulesService {

    /**
     * Создает новую запись о копсуле
     *
     * @param capsule - капсула для создания
     */

    void createCapsule(Capsule capsule);

    /**
     * Возвращает список всех имеющихся кпсул
     *
     * @return список капсул
     */
    List<Capsule> readAllCapsules();

    /**
     * Возвращает капсулу по её ID
     *
     * @param capsuleId - ID капсулы
     * @return - объект капсулы с заданным ID
     */
    Capsule readCapsulesById(UUID capsuleId);

    /**
     * Удаляет капсулу с заданным ID
     *
     * @param capsuleId - id капсулы, которую нужно удалить
     * @return - true если капсула была удалена, иначе false
     */
    boolean deleteClothesById(UUID capsuleId);

    /**
     * Обновляет капсулу с заданным ID,
     * в соответствии с переданной капсулой
     *
     * @param capsule - капсула в соответсвии с которой нужно обновить данные
     * @param id       - id капсулы которую нужно обновить
     */
    void update(Capsule capsule, UUID id);


    /**
     * Возвращает список капсул в которых находится определённая вещь
     *
     * @param clothesId - вещь, которая содержится в капсулах
     */
    List<Capsule> getCapsulesWhereClothes(UUID clothesId);

}
