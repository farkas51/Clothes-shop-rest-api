package com.yellowhouse.startuppostgressdocker;

import com.yellowhouse.startuppostgressdocker.models.OrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yellowhouse.startuppostgressdocker.steps.OrderSteps.*;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getOrderCreationBody;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class OrdersTests {

    @DisplayName("Дано: Корректное тело запроса " +
            "Когда: POST /orders " +
            "Тогда: СК200, заказ создан, вернулся объект заказа")

    @Test
    void testCreateOrder() {

        OrderResponse order = createOrder(getOrderCreationBody());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(order)
                            .withFailMessage("Заказ не создалась")
                            .isNotNull();
                }
        );

        deleteOrderById(order.getId());
    }

    @DisplayName("Дано:  " +
            "Когда: GET /orders " +
            "Тогда: СК200, вернулся список всех заказов")

    @Test
    void testGetAllOrders() {

        List<OrderResponse> orderResponses = getAllOrders();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(orderResponses.size())
                            .withFailMessage("Не вернулся список всех заказов")
                            .isNotNull();
                }
        );
    }

    @DisplayName("Дано: Валидный id созданного заказа " +
            "Когда: GET /orders/{id} " +
            "Тогда: СК200, вернулся заказ")

    @Test
    void testGetOrderById() {

        OrderResponse createdOrder = createOrder(getOrderCreationBody());

        OrderResponse gettedOrder = getOrderById(createdOrder.getId());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(createdOrder.getId())
                            .withFailMessage("Не вернулся список всех вещей")
                            .isEqualTo(gettedOrder.getId());
                }
        );
        deleteOrderById(createdOrder.getId());
    }


    @DisplayName("Дано: Валидный id созданного заказа  " +
            "Когда: DELETE /orders/{id} " +
            "Тогда: СК200, заказ удален")

    @Test
    void testDeleteClothesById() {

        OrderResponse createdOrder = createOrder(getOrderCreationBody());

        deleteOrderById(createdOrder.getId());

    }

    @DisplayName("Дано: Валидный id пользователя, у которого есть заказ " +
            "Когда: GET /orders/user-orders " +
            "Тогда: СК200, вернулся список всех заказов")

    @Test
    void testGetOrderByClient() {

        OrderResponse createdOrder = createOrder(getOrderCreationBody());

        List<OrderResponse> orderResponses = getAllOrdersByClient(createdOrder.getUserId());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(orderResponses.get(0))
                            .withFailMessage("Не вернулся список всех заказов")
                            .isEqualTo(createdOrder);
                }
        );
    }

    @DisplayName("Дано: Дата, по которой нам нужно вернуть времена доставок" +
            "Когда: GET /orders/get-deliveries-by-date " +
            "Тогда: СК200, вернулся список всех заказов")

    @Test
    void testGetDeliveriesByDate() {


        List<String> deliveries = getAllDeliveries("2022-12-13");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(deliveries.size())
                            .withFailMessage("Не вернулся список доставок")
                            .isNotNull();
                }
        );
    }


}
