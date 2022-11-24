package com.yellowhouse.startuppostgressdocker.repository.users;

import com.yellowhouse.startuppostgressdocker.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    Users getByEmail(String email);

    Users getByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberOrEmail(String email,String phoneNumber);

}

