package com.yellowhouse.startuppostgressdocker.steps;

import com.yellowhouse.startuppostgressdocker.ApiConfig;
import com.yellowhouse.startuppostgressdocker.models.OrderRequest;
import com.yellowhouse.startuppostgressdocker.models.OrderResponse;
import com.yellowhouse.startuppostgressdocker.models.UsersRequest;
import com.yellowhouse.startuppostgressdocker.models.UsersResponse;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UsersSteps extends ApiConfig {

    @Step("Создание пользователя")
    public static UsersResponse createUser(UsersRequest body) {
        return given(requestSpecification)
                .body(body)
                .when()
                .post(BASE_PATH_USERS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse.class);
    }

    @Step("Получение всех пользователей")
    public static List<UsersResponse> getAllUsers() {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_USERS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse[].class));
    }

    @Step("Удаление пользователя по id")
    public static void deleteUserById(UUID userId) {
        given(requestSpecification)
                .when()
                .delete(BASE_PATH_USERS + "/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Step("Получение пользователя по id")
    public static UsersResponse getUserById(UUID userId) {
        return given(requestSpecification)
                .when()
                .get(BASE_PATH_USERS + "/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse.class);
    }

    @Step("Получение пользователя по email")
    public static UsersResponse getUserByEmail(String email) {
        return given(requestSpecification)
                .when()
                .param("email",email)
                .get(BASE_PATH_USERS + "/user-by-email")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse.class);
    }

    @Step("Получение пользователя по phoneNumber")
    public static UsersResponse getUserByPhoneNumber(String phoneNumber) {
        return given(requestSpecification)
                .when()
                .param("phoneNumber",phoneNumber)
                .get(BASE_PATH_USERS + "/user-by-phone-number")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse.class);
    }

    @Step("Проверка регистрации пользователя")
    public static boolean getUserRegistered(String email,String phoneNumber) {
        return given(requestSpecification)
                .when()
                .param("email",email)
                .param("phoneNumber",phoneNumber)
                .get(BASE_PATH_USERS + "/registered")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(Boolean.class);
    }
}
