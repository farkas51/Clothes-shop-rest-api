package com.yellowhouse.startuppostgressdocker.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UsersRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
}
