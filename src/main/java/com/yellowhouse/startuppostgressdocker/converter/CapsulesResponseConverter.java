package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsuleResponse;

public interface CapsulesResponseConverter {

    /**
     * Преобразует модель капсулы в корректный объект для ответа в сервисе
     *
     * @param source - исходный ответ капсулы
     */

    CapsuleResponse convert(Capsule source);
}
