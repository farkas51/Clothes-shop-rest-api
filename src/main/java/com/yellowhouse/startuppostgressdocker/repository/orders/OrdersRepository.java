package com.yellowhouse.startuppostgressdocker.repository.orders;

import com.yellowhouse.startuppostgressdocker.model.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository

public interface OrdersRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUserId(UUID userId);

}
