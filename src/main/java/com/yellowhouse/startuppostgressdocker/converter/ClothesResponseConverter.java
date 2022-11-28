package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.model.clothes.ClothesResponse;

public interface ClothesResponseConverter {

    /**
     * Преобразует модель вещи в корректный объект для ответа в сервисе
     *
     * @param source - исходный ответ вещи
     */

    ClothesResponse convert(Clothes source);

}
