package com.yellowhouse.startuppostgressdocker.model.clothes;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder

public class ClothesResponse {
    private UUID id;
    private String type;
    private String name;
    private int size;
    private int price;
    private boolean inCapsula;
    private int wear;
    private int status;
    private int coolKoef;

    private Set<UUID> capsulesWereThisClothesIds;
}
