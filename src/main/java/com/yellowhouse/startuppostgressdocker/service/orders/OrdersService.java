package com.yellowhouse.startuppostgressdocker.service.orders;

import com.yellowhouse.startuppostgressdocker.model.orders.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrdersService {

    /**
     * Создает новую запись о заказе
     *
     * @param order - заказ для создания
     */

    void createOrder(Order order);


    /**
     * Возвращает список всех имеющихся заказов
     *
     * @return список заказов
     */
    List<Order> readAllOrders();

    /**
     * Возвращает заказ по его ID
     *
     * @param orderId - ID заказа
     * @return - объект заказа с заданным ID
     */
    Order readOrderById(UUID orderId);

    /**
     * Удаляет заказ с заданным ID
     *
     * @param orderId - id заказа, который нужно удалить
     * @return - true если заказ была удален, иначе false
     */
    boolean deleteOrderById(UUID orderId);

    /**
     * Обновляет заказ с заданным ID,
     * в соответствии с переданным заказом
     *
     * @param order   - заказ в соответсвии с которым нужно обновить данные
     * @param orderId - id заказа который нужно обновить
     */
    void update(Order order, UUID orderId);

    /**
     * Обновляет заказ с заданным ID, по конкретным полям
     * в соответствии с переданными полями
     *
     * @param fields  - поля, которые необходимо обновить в сущеости заказа
     * @param orderId - id заказа который нужно обновить
     * @return - order - обьект заказа с обновленными полями
     */

    Order patch(UUID orderId, Map<Object, Object> fields);

    /**
     * Получает список заказов по данному id пользователя
     *
     * @param userId - id пользователя, чьи заказы нужно найти
     * @return - orders - список заказов данного пользователя
     */
    List<Order> readOrderByUserId(UUID userId);

    /**
     * Получает список доставок в определённый день
     *
     * @param dateOfDelivery  - дата, по которой нужно найти заказы (формат ГГ-ММ-ДД)
     * @return - orders - список дат удовлетворяющий условию
     */
    List<String> getDeliveryDateTimeByDate(String dateOfDelivery);

    /**
     * Получает список заказов в определённом статусе
     *
     * @param status  - статус
     * @return orders - список заказов удовлетворяющий условию
     */
    List<Order> readOrderByStatus(String status);
}
