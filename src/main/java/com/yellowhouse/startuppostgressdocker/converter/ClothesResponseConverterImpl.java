package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import com.yellowhouse.startuppostgressdocker.model.clothes.ClothesResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClothesResponseConverterImpl implements ClothesResponseConverter {

    @Override
    public ClothesResponse convert(Clothes source) {
        return ClothesResponse.builder()
                .price(source.getPrice())
                .size(source.getSize())
                .id(source.getId())
                .type(source.getType())
                .wear(source.getWear())
                .name(source.getName())
                .status(source.getStatus())
                .coolKoef(source.getCoolKoef())
                .inCapsula(source.isInCapsula())
                .capsulesWereThisClothesIds(
                        source.getCapsules().stream().map(Capsule::getId).collect(Collectors.toSet())
                )
                .build();
    }
}
