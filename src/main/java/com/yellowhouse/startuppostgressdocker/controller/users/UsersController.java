package com.yellowhouse.startuppostgressdocker.controller.users;

import com.yellowhouse.startuppostgressdocker.model.users.Users;
import com.yellowhouse.startuppostgressdocker.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    public UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> addUsers(@RequestBody Users users) {
        usersService.createUser(users);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAll() {
        return ResponseEntity.ok(usersService.readAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findUsersById(@PathVariable(value = "id") UUID userId) {
        Users users = usersService.readUserById(userId);
        return ResponseEntity.ok().body(users);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable(value = "id") UUID userId) {
        boolean flag = false;
        if (usersService.deleteUserById(userId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/registered")
    public ResponseEntity<Boolean> isUserRegistered(@RequestParam(value = "email") String email,
                                                    @RequestParam(value = "phoneNumber") String phoneNumber) {
        if (usersService.isRegistered(email, phoneNumber))
            return ResponseEntity.ok(Boolean.TRUE);
        else return ResponseEntity.ok(Boolean.FALSE);
    }

    @GetMapping("/user-by-phone-number")
    public Users findUserByPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber) {
        Users user = usersService.readUserByPhoneNumber(phoneNumber);
        return user;
    }

    @GetMapping("/user-by-email")
    public Users findUserByEmail(@RequestParam(value = "email") String email) {
        Users user = usersService.readUserByEmail(email);
        return user;
    }
}
