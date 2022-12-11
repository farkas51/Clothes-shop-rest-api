package com.yellowhouse.startuppostgressdocker.service.users;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.orders.Order;
import com.yellowhouse.startuppostgressdocker.model.users.Users;
import com.yellowhouse.startuppostgressdocker.repository.users.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    public UsersRepository usersRepository;


    @Override
    public void createUser(Users user) {
        usersRepository.save(user);
        log.info("Создана запись о пользователе ");
    }

    @Override
    public List<Users> readAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        log.info("Возвращены все пользователи");
        return new ArrayList<>(usersList);
    }

    @Override
    public Boolean isRegistered(String email, String phoneNumber) {
        boolean isExistByEmailOrByPhoneNumber;

        isExistByEmailOrByPhoneNumber = usersRepository.existsByEmailOrPhoneNumber(email, phoneNumber);

        if (isExistByEmailOrByPhoneNumber) {
            log.info("Пользователь уже зарегистрирован");
            return true;
        } else {
            log.info("Пользователь не зарегистрирован");
            return false;
        }
    }

    @Override
    public Users readUserById(UUID userId) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users not found " + userId));
        log.info("Получен пользователь " + userId);
        return user;
    }

    @Override
    public boolean deleteUserById(UUID userId) throws ResourceNotFoundException {

        boolean flag = false;
        try {
            usersRepository.deleteById(userId);
        } catch (Exception e) {
            log.info("Пользователь не найден");
            throw new ResourceNotFoundException("User not found" + userId);
        }

        log.info("Удален пользователь " + userId);
        return flag = true;
    }

    @Override
    public Users readUserByPhoneNumber(String phoneNumber) {
        try {
            Users user = usersRepository.getByPhoneNumber(phoneNumber);
            log.info("Получен пользователь " + phoneNumber);
            return user;
        } catch (Exception e) {
            log.info("Пользователь не найден");
            throw new ResourceNotFoundException("User not found" + phoneNumber);
        }
    }

    @Override
    public Users readUserByEmail(String email) {
        try {
            Users user = usersRepository.getByEmail(email);
            log.info("Получен пользователь " + email);
            return user;
        } catch (Exception e) {
            log.info("Пользователь не найден");
            throw new ResourceNotFoundException("User not found" + email);
        }
    }

    @Override
    public Users patch(UUID userId, Map<Object, Object> fields) {
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Users.class, key.toString());
                field.setAccessible(true);
                if (field.getType().equals(UUID.class)) {
                    ReflectionUtils.setField(field, user.get(), UUID.fromString(value.toString()));
                } else if (field.getType().equals(int.class)) {
                    ReflectionUtils.setField(field, user.get(), Integer.valueOf(value.toString()));
                } else if (field.getType().equals(LocalDateTime.class)) {
                    ReflectionUtils.setField(field, user.get(), LocalDateTime.parse(value.toString()));

                } else {
                    ReflectionUtils.setField(field, user.get(), value.toString());
                }
            });
            Users updatedUser = usersRepository.save(user.get());
            return updatedUser;
        } else {
            throw new ResourceNotFoundException("Запись с заказом по заданному id не найдена");
        }
    }

}
