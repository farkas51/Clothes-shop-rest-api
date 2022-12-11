package com.yellowhouse.startuppostgressdocker.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UsersResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String createdAt;
    private String updatedAt;
    private String password;
}
