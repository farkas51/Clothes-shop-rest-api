package com.yellowhouse.startuppostgressdocker.model.users;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
@Data
@Builder
public class UserResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
}
