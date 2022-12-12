package com.yellowhouse.startuppostgressdocker.controller.users;

import com.yellowhouse.startuppostgressdocker.converter.UserResponseConverter;
import com.yellowhouse.startuppostgressdocker.model.users.UserResponse;
import com.yellowhouse.startuppostgressdocker.model.users.Users;
import com.yellowhouse.startuppostgressdocker.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    public UsersService usersService;

    @Autowired
    public UserResponseConverter converter;

    @PostMapping
    public ResponseEntity<UserResponse> addUsers(@RequestBody Users users) {
        usersService.createUser(users);
        UserResponse userResponse = converter.convert(users);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {

        List<UserResponse> usersResponse = usersService.readAllUsers().stream()
                .map(capsules -> converter.convert(capsules))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersResponse);
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

    @PatchMapping("/{id}")
    public Users patchOrder(@PathVariable(value = "id") UUID id, @RequestBody Map<Object, Object> fields) {
        Users patchedUser = usersService.patch(id, fields);

        return patchedUser;
    }
}
