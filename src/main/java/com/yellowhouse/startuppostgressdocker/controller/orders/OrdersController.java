package com.yellowhouse.startuppostgressdocker.controller.orders;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsuleResponse;
import com.yellowhouse.startuppostgressdocker.model.orders.Order;
import com.yellowhouse.startuppostgressdocker.repository.orders.OrdersRepository;
import com.yellowhouse.startuppostgressdocker.service.orders.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping()
    public Order addOrder(@RequestBody Order order){
        ordersService.createOrder(order);
        return order;
    }

    @GetMapping()
    public List<Order> findAll() {
        List<Order> orderList = ordersService.readAllOrders();
        return orderList;
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable(value = "id") UUID orderId) {
        Order order = ordersService.readOrderById(orderId);
        return order;
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteOrders(@PathVariable(value = "id") UUID orderId) {
        if (ordersService.deleteOrderById(orderId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public Order patchOrder(@PathVariable(value = "id") UUID id, @RequestBody Map<Object,Object> fields){
        Order patchedOrder = ordersService.patch(id,fields);
        return patchedOrder;
    }

}
