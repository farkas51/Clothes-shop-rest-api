package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.users.UserResponse;
import com.yellowhouse.startuppostgressdocker.model.users.Users;

public interface UserResponseConverter {

    /**
     * Преобразует модель пользователя в обьект ответа сервиса без пароля
     *
     * @param source - исходный ответ с пользователем
     */

    UserResponse convert(Users source);
}
