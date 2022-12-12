package com.yellowhouse.startuppostgressdocker.steps;

import com.yellowhouse.startuppostgressdocker.ApiConfig;
import com.yellowhouse.startuppostgressdocker.models.CapsuleRequest;
import com.yellowhouse.startuppostgressdocker.models.CapsulesResponse;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CapsulesSteps extends ApiConfig {

    @Step("Создание капсулы")
    public static CapsulesResponse createCapsule(CapsuleRequest body) {
        return given(requestSpecification)
                .body(body)
                .when()
                .post(BASE_PATH_CAPSULES)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CapsulesResponse.class);
    }

    @Step("Получение всех капсул")
    public static List<CapsulesResponse> getAllCapsules() {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_CAPSULES)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CapsulesResponse[].class));
    }

    @Step("Получение капсулы по id")
    public static CapsulesResponse getCapsuleById(UUID capsuleId) {
        return given(requestSpecification)
                .when()
                .get(BASE_PATH_CAPSULES + "/" + capsuleId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CapsulesResponse.class);
    }

    @Step("Получение капсулы по id")
    public static void deleteCapsuleById(UUID capsuleId) {
        given(requestSpecification)
                .when()
                .delete(BASE_PATH_CAPSULES + "/" + capsuleId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Step("Положить вещь в капсулу")
    public static void putClothesInCapsula(UUID capsuleId, UUID clothesId) {
        given(requestSpecification)
                .when()
                .param("capsuleId", capsuleId)
                .param("clothesId", clothesId)
                .get(BASE_PATH_CAPSULES + "/add-clothes-to-capsule")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
