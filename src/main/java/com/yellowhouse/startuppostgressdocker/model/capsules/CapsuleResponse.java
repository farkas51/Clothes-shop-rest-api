package com.yellowhouse.startuppostgressdocker.model.capsules;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CapsuleResponse {

    private UUID id;
    private String type;
    private int price;
    private int size;
    private String clothesSize;

    private Set<UUID> clothesInCapsulaIds;

}
