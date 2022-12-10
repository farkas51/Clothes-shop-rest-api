package com.yellowhouse.startuppostgressdocker.converter;

import com.yellowhouse.startuppostgressdocker.model.users.UserResponse;
import com.yellowhouse.startuppostgressdocker.model.users.Users;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverterImpl implements UserResponseConverter{

    @Override
    public UserResponse convert(Users source) {
        return UserResponse.builder()
                .id(source.getId())
                .email(source.getEmail())
                .address(source.getAddress())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .build();

    }
}
