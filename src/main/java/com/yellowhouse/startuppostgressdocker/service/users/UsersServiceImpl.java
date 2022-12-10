package com.yellowhouse.startuppostgressdocker.service.users;

import com.yellowhouse.startuppostgressdocker.controller.ResourceNotFoundException;
import com.yellowhouse.startuppostgressdocker.model.users.Users;
import com.yellowhouse.startuppostgressdocker.repository.users.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        isExistByEmailOrByPhoneNumber = usersRepository.existsByPhoneNumberOrEmail(email, phoneNumber);

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

}
