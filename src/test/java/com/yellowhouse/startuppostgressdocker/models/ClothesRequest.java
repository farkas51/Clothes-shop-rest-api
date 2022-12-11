package com.yellowhouse.startuppostgressdocker.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClothesRequest {

    private String type;
    private String name;
    private int size;
    private int price;
    private boolean inCapsula;
    private int wear;
    private int status;
    private int coolKoef;
}
