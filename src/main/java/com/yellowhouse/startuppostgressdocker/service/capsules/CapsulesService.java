package com.yellowhouse.startuppostgressdocker.service.capsules;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;

import java.util.List;
import java.util.UUID;

public interface CapsulesService {

    /**
     * Создает новую запись о копсуле
     *
     * @param capsule - капсула для создания
     */

    void createCapsule(Capsules capsule);

    /**
     * Возвращает список всех имеющихся кпсул
     *
     * @return список капсул
     */
    List<Capsules> readAllCapsules();

    /**
     * Возвращает капсулу по её ID
     *
     * @param capsuleId - ID капсулы
     * @return - объект капсулы с заданным ID
     */
    Capsules readCapsulesById(UUID capsuleId);

    /**
     * Удаляет капсулу с заданным ID
     *
     * @param capsuleId - id капсулы, которую нужно удалить
     * @return - true если капсула была удалена, иначе false
     */
    boolean deleteClothesById(UUID capsuleId);

}
