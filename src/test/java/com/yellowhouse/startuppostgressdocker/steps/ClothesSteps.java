package com.yellowhouse.startuppostgressdocker.steps;

import com.yellowhouse.startuppostgressdocker.ApiConfig;
import com.yellowhouse.startuppostgressdocker.models.ClothesRequest;
import com.yellowhouse.startuppostgressdocker.models.ClothesResponse;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ClothesSteps extends ApiConfig {

    @Step("Получение вещи по id")
    public static ClothesResponse getClothesById(UUID capsuleId) {
        return given(requestSpecification)
                .when()
                .get(BASE_PATH_CLOTHES + "/" + capsuleId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(ClothesResponse.class);
    }

    @Step("Создание вещи")
    public static ClothesResponse createClothes(ClothesRequest body) {
        return given(requestSpecification)
                .body(body)
                .when()
                .post(BASE_PATH_CLOTHES)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(ClothesResponse.class);
    }

    @Step("Получение всех вещей")
    public static List<ClothesResponse> getAllClothes() {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_CLOTHES)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(ClothesResponse[].class));
    }

    @Step("Получение вещи по id")
    public static void deleteClothesById(UUID capsuleId) {
        given(requestSpecification)
                .when()
                .delete(BASE_PATH_CLOTHES + "/" + capsuleId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Step("Получение всех вещей в определённом статусе")
    public static List<ClothesResponse> getClothesInStatus(String status) {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_CLOTHES + "/clothes-in-status/" + status)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(ClothesResponse[].class));
    }
}
