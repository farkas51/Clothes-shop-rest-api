package com.yellowhouse.startuppostgressdocker.utils;

import com.yellowhouse.startuppostgressdocker.models.CapsuleRequest;
import com.yellowhouse.startuppostgressdocker.models.ClothesRequest;
import com.yellowhouse.startuppostgressdocker.models.OrderRequest;
import com.yellowhouse.startuppostgressdocker.models.UsersRequest;

import java.util.UUID;

public class TestObjectBuilder {

    public static CapsuleRequest getCapsuleCreationBody() {
        return CapsuleRequest.builder()
                .type("api-test")
                .size(1337)
                .clothesSize("API TEST")
                .status("NEW")
                .price(100)
                .build();
    }

    public static CapsuleRequest getCapsuleCreationBody(String type, int size) {
        return CapsuleRequest.builder()
                .type(type)
                .size(size)
                .clothesSize("API TEST")
                .status("NEW")
                .price(100)
                .build();
    }

    public static ClothesRequest getClothesCreationBody() {
        return ClothesRequest.builder()
                .wear(1)
                .type("api-test")
                .name("api-test")
                .size(1)
                .price(1)
                .coolKoef(1)
                .inCapsula(false)
                .status(1)
                .build();
    }

    public static OrderRequest getOrderCreationBody() {
        return OrderRequest.builder()
                .deliveryDateToClient("2022-12-12T17:15:17.262")
                .deliveryDateBack("")
                .capsuleId(UUID.randomUUID())
                .price(100)
                .size(12)
                .userId(UUID.randomUUID())
                .build();
    }

    public static UsersRequest getUserCreationBody() {
        return UsersRequest.builder()
                .email("test@test.com")
                .address("test")
                .phoneNumber("9537511791")
                .lastName("Тестовый")
                .firstName("Тест")
                .password("qwerty")
                .build();
    }
}
