package com.yellowhouse.startuppostgressdocker.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CapsuleRequest {
    private String type;
    private int price;
    private int size;
    private String clothesSize;
    private String status;
}
