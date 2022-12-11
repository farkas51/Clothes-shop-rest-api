package com.yellowhouse.startuppostgressdocker;

import com.yellowhouse.startuppostgressdocker.models.OrderResponse;
import com.yellowhouse.startuppostgressdocker.models.UsersResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yellowhouse.startuppostgressdocker.steps.OrderSteps.*;
import static com.yellowhouse.startuppostgressdocker.steps.UsersSteps.*;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getOrderCreationBody;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getUserCreationBody;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserTests {
    @DisplayName("Дано: Корректное тело запроса " +
            "Когда: POST /users " +
            "Тогда: СК200, пользователь создан, вернулся объект пользователя")

    @Test
    void testCreateUser() {

        UsersResponse user = createUser(getUserCreationBody());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(user)
                            .withFailMessage("Пользователь не создался")
                            .isNotNull();
                }
        );

        deleteUserById(user.getId());
    }

    @DisplayName("Дано:  " +
            "Когда: GET /users " +
            "Тогда: СК200, вернулся список всех пользователей")

    @Test
    void testGetAllUsers() {

        List<UsersResponse> usersResponses = getAllUsers();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(usersResponses.size())
                            .withFailMessage("Не вернулся список всех пользователей")
                            .isNotNull();
                }
        );
    }

    @DisplayName("Дано: Валидный id созданного пользователя " +
            "Когда: GET /users/{id} " +
            "Тогда: СК200, вернулся пользователь")

    @Test
    void testGetUserById() {

        UsersResponse user = createUser(getUserCreationBody());

        UsersResponse gettedUser = getUserById(user.getId());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(user.getId())
                            .withFailMessage("Не вернулся корректный пользователь")
                            .isEqualTo(gettedUser.getId());
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный id созданного пользователя " +
            "Когда: DELETE /users/{id} " +
            "Тогда: СК200, пользователь удалён")

    @Test
    void testDeleteUserById(){
        UsersResponse user = createUser(getUserCreationBody());

        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный email созданного пользователя " +
            "Когда: GET /users/user-by-email " +
            "Тогда: СК200, вернулся пользователь")
    @Test
    void testGetUserByEmail() {

        UsersResponse user = createUser(getUserCreationBody());

        UsersResponse gettedUser = getUserByEmail("test@test.com");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(user.getId())
                            .withFailMessage("Не вернулся корректный пользователь")
                            .isEqualTo(gettedUser.getId());
                    softAssertions
                            .assertThat(user.getEmail())
                            .withFailMessage("Не вернулся корректный пользователь")
                            .isEqualTo(gettedUser.getEmail());
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный phoneNumber созданного пользователя " +
            "Когда: GET /users/user-by-phone-number " +
            "Тогда: СК200, вернулся пользователь")
    @Test
    void testGetUserByPhoneNumber() {

        UsersResponse user = createUser(getUserCreationBody());

        UsersResponse gettedUser = getUserByPhoneNumber("9537511791");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(user.getId())
                            .withFailMessage("Не вернулся корректный пользователь")
                            .isEqualTo(gettedUser.getId());
                    softAssertions
                            .assertThat(user.getPhoneNumber())
                            .withFailMessage("Не вернулся корректный пользователь")
                            .isEqualTo(gettedUser.getPhoneNumber());
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный phoneNumber или email " +
            "Когда: GET /users/registered, дан только email " +
            "Тогда: СК200, вернулся флаг регистрации пользователя")
    @Test
    void testGetUserRegisteredByOnlyEmail(){
        UsersResponse user = createUser(getUserCreationBody());

        Boolean flag = getUserRegistered("test@test.com","");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(flag)
                            .withFailMessage("Некорректная информация о регистрации пользователя")
                            .isTrue();
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный phoneNumber или email " +
            "Когда: GET /users/registered, дан только phoneNumber " +
            "Тогда: СК200, вернулся положительный флаг регистрации пользователя")
    @Test
    void testGetUserRegisteredByOnlyPhoneNumber(){
        UsersResponse user = createUser(getUserCreationBody());

        Boolean flag = getUserRegistered("","9537511791");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(flag)
                            .withFailMessage("Некорректная информация о регистрации пользователя")
                            .isTrue();
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Валидный phoneNumber или email " +
            "Когда: GET /users/registered " +
            "Тогда: СК200, вернулся положительный флаг регистрации пользователя")
    @Test
    void testGetUserRegisteredByOnlyPhoneNumberAndEmail(){
        UsersResponse user = createUser(getUserCreationBody());

        Boolean flag = getUserRegistered("test@test.com","9537511791");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(flag)
                            .withFailMessage("Некорректная информация о регистрации пользователя")
                            .isTrue();
                }
        );
        deleteUserById(user.getId());
    }

    @DisplayName("Дано: Невалидный email и phoneNumber " +
            "Когда: GET /users/registered " +
            "Тогда: СК200, вернулся отрицательный флаг  регистрации пользователя")
    @Test
    void testGetUserRegisteredByOnlyPhoneNumberAndEmailNegative(){
        UsersResponse user = createUser(getUserCreationBody());

        Boolean flag = getUserRegistered("test@tes3t.com","95375112791");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(flag)
                            .withFailMessage("Некорректная информация о регистрации пользователя")
                            .isFalse();
                }
        );
        deleteUserById(user.getId());
    }


}
