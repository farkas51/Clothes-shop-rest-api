package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsulesResponse;

public interface CapsulesResponseConverter {

    /**
     * Преобразует модель капсулы в корректный объект для ответа в сервисе
     *
     * @param source - исходный ответ капсулы
     */

    CapsulesResponse convert(Capsules source);
}
