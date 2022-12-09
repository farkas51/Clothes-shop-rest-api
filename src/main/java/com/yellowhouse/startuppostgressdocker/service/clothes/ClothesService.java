package com.yellowhouse.startuppostgressdocker.service.clothes;

import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ClothesService {

    /**
     * Создает новую запись о вещи
     *
     * @param clothes - вещь для создания
     */

    void createClothes(Clothes clothes);


    /**
     * Возвращает список всех имеющихся вещей
     *
     * @return список вещей
     */
    List<Clothes> readAllClothes();

    /**
     * Возвращает вещь по её ID
     *
     * @param id - ID вещи
     * @return - объект вещи с заданным ID
     */
    Clothes readClotheById(UUID id);

    /**
     * Удаляет вещь с заданным ID
     *
     * @param id - id вещи, которую нужно удалить
     * @return - true если вещь была удалена, иначе false
     */
    boolean deleteClothesById(UUID id);

    /**
     * Обновляет вещь с заданным ID,
     * в соответствии с переданной вещью
     *
     * @param clothes - вещь в соответсвии с которой нужно обновить данные
     * @param id      - id вещи которого нужно обновить
     */
    void update(Clothes clothes, UUID id);

    /**
     * Получает список вещей в заданном статусе,
     *
     * @param status - статус вещи
     * @return - список вещей в заданном status
     */
    List<Clothes> findByStatus(Integer status);

    /**
     * Возвращает список вещей которые входят в определённую капсулу,
     *
     * @param capsuleId - капсула, в которой содержатся вещи
     * @return - список вещей в заданном status
     */
    List<Clothes> getClothesWhereCapsules(UUID capsuleId);

    /**
     * Обновляет вещь с заданным ID, по конкретным полям
     * в соответствии с переданными полями
     *
     * @param fields    - поля, которые необходимо обновить в сущности вещи
     * @param clothesId - id вещи который нужно обновить
     * @return - order - обьект вещи с обновленными полями
     */

    Clothes patch(UUID clothesId, Map<Object, Object> fields);
}
