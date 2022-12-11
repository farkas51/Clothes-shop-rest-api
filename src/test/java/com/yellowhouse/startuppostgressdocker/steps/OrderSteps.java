package com.yellowhouse.startuppostgressdocker.steps;

import com.yellowhouse.startuppostgressdocker.ApiConfig;
import com.yellowhouse.startuppostgressdocker.models.ClothesRequest;
import com.yellowhouse.startuppostgressdocker.models.ClothesResponse;
import com.yellowhouse.startuppostgressdocker.models.OrderRequest;
import com.yellowhouse.startuppostgressdocker.models.OrderResponse;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class OrderSteps extends ApiConfig {

    @Step("Получение заказа по id")
    public static OrderResponse getOrderById(UUID orderId) {
        return given(requestSpecification)
                .when()
                .get(BASE_PATH_ORDERS + "/" + orderId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(OrderResponse.class);
    }

    @Step("Создание заказа")
    public static OrderResponse createOrder(OrderRequest body) {
        return given(requestSpecification)
                .body(body)
                .when()
                .post(BASE_PATH_ORDERS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(OrderResponse.class);
    }

    @Step("Получение всех заказов")
    public static List<OrderResponse> getAllOrders() {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_ORDERS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(OrderResponse[].class));
    }

    @Step("Удаление заказа по id")
    public static void deleteOrderById(UUID orderId) {
        given(requestSpecification)
                .when()
                .delete(BASE_PATH_ORDERS + "/" + orderId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Step("Получение всех заказов клиента")
    public static List<OrderResponse> getAllOrdersByClient(UUID clientId) {
        return List.of(given(requestSpecification)
                .when()
                .get(BASE_PATH_ORDERS + "/user-orders/" + clientId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(OrderResponse[].class));
    }

    @Step("Получение списка доставок по дате")
    public static List<String> getAllDeliveries(String date) {
        return List.of(given(requestSpecification)
                .when()
                .param("date",date)
                .get(BASE_PATH_ORDERS + "/get-deliveries-by-date")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(String[].class));
    }
}
