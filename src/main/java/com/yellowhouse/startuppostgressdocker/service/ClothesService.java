package com.yellowhouse.startuppostgressdocker.service;

import com.yellowhouse.startuppostgressdocker.model.Clothes;

import java.util.List;
import java.util.UUID;

public interface ClothesService {

    /**
     * Создает новую запись о вещи
     * @param clothes - вещь для создания
     */

    void createClothes(Clothes clothes);


    /**
     * Возвращает список всех имеющихся вещей
     * @return список вещей
     */
    List<Clothes> readAllClothes();

    /**
     * Возвращает вещь по её ID
     * @param id - ID вещи
     * @return - объект вещи с заданным ID
     */
    Clothes readClotheById(UUID id);

    /**
     * Удаляет вещь с заданным ID
     * @param id - id вещи, которую нужно удалить
     * @return - true если вещь была удалена, иначе false
     */
    boolean deleteClothesById(UUID id);
//
//    /**
//     * Обновляет вещь с заданным ID,
//     * в соответствии с переданной вещью
//     * @param clothes - вещь в соответсвии с которой нужно обновить данные
//     * @param id - id вещи которого нужно обновить
//     * @return - true если данные были обновлены, иначе false
//     */
//    boolean update(Clothes clothes, UUID id);
//

}
