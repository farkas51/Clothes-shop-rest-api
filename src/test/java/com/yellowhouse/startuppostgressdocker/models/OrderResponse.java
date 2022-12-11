package com.yellowhouse.startuppostgressdocker.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {

    private UUID id;
    private String createdAt;
    private String updatedAt;
    private UUID userId;
    private UUID capsuleId;
    private int price;
    private int size;
    private String deliveryDateToClient;
    private String deliveryDateBack;
    private String status;
}
