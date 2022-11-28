package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;
import com.yellowhouse.startuppostgressdocker.model.capsules.CapsulesResponse;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CapsulesResponseConverterImpl implements CapsulesResponseConverter {

    @Override
    public CapsulesResponse convert(Capsules source) {

        return CapsulesResponse.builder()
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
