package com.yellowhouse.startuppostgressdocker.service.capsules;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsuleResponse;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * @param id      - id капсулы которую нужно обновить
     */
    void update(Capsule capsule, UUID id);


    /**
     * Возвращает список капсул в которых находится определённая вещь
     *
     * @param clothesId - вещь, которая содержится в капсулах
     */
    List<Capsule> getCapsulesWhereClothes(UUID clothesId);

    /**
     * Возвращает список размеров вещей в капсулах, найденных по size,type
     *
     * @param size - размер капсулы
     * @param type - тип капсулы
     */
    Set<String> getSizesInCapsulaByTypeAndStyle(String size, String type);

    /**
     * Возвращает случайную капсулу, меняет статус капсулы
     *
     * @param size - размер капсулы
     * @param type - тип капсулы
     */
    Capsule getRandomCapsula(String size, String type);

    /**
     * Обновляет капсулу с заданным ID, по конкретным полям
     * в соответствии с переданными полями
     *
     * @param fields    - поля, которые необходимо обновить в сущности капсулы
     * @param capsuleId - id капсулы который нужно обновить
     * @return - capsule - обьект капсулы с обновленными полями
     */

    Capsule patch(UUID capsuleId, Map<Object, Object> fields);

}
