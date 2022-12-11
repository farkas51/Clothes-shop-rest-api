package com.yellowhouse.startuppostgressdocker.service.users;

import com.yellowhouse.startuppostgressdocker.model.orders.Order;
import com.yellowhouse.startuppostgressdocker.model.users.Users;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UsersService {

    /**
     * Создает новую запись о пользователе
     *
     * @param user - пользователь для создания
     */

    void createUser(Users user);

    /**
     * Возвращает список всех имеющихся пользователей
     *
     * @return список пользователей
     */
    List<Users> readAllUsers();

    /**
     * Проверка регистрации пользователя
     *
     * @param email, phoneNumber - email и/или номер телефона пользователя
     * @return true - пользователь зарегистрирован, иначе false
     */
    Boolean isRegistered(String email, String phoneNumber);

    /**
     * Возвращает пользователя по его ID
     *
     * @param id - ID пользоватееля
     * @return - объект пользователя с заданным ID
     */
    Users readUserById(UUID id);

    /**
     * Удаляет пользователя с заданным ID
     *
     * @param id - id пользователя, которого нужно удалить
     * @return - true если пользователь был удален, иначе false
     */
    boolean deleteUserById(UUID id);

    /**
     * Возвращает пользователя по его phoneNumber
     *
     * @param phoneNumber - Номер телефона пользоватееля
     * @return - объект пользователя с заданным номером телефона
     */
    Users readUserByPhoneNumber(String phoneNumber);

    /**
     * Возвращает пользователя по его Email
     *
     * @param email - Номер телефона пользоватееля
     * @return - объект пользователя с заданным номером телефона
     */
    Users readUserByEmail(String email);

    /**
     * Обновляет заказ с заданным ID, по конкретным полям
     * в соответствии с переданными полями
     *
     * @param fields  - поля, которые необходимо обновить в сущеости заказа
     * @param userId - id заказа который нужно обновить
     * @return - order - обьект заказа с обновленными полями
     */

    Users patch(UUID userId, Map<Object, Object> fields);
}
