package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsuleResponse;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CapsulesResponseConverterImpl implements CapsulesResponseConverter {

    @Override
    public CapsuleResponse convert(Capsule source) {

        return CapsuleResponse.builder()
                .id(source.getId())
                .size(source.getSize())
                .price(source.getPrice())
                .type(source.getType())
                .clothesInCapsulaIds(
                        source.getClothesInCapsula().stream().map(Clothes::getId).collect(Collectors.toSet())
                )
                .build();
    }
}
