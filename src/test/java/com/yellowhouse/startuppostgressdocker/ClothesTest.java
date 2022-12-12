package com.yellowhouse.startuppostgressdocker;

import com.yellowhouse.startuppostgressdocker.models.ClothesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yellowhouse.startuppostgressdocker.steps.ClothesSteps.*;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getClothesCreationBody;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ClothesTest {

    @DisplayName("Дано: Корректное тело запроса " +
            "Когда: POST /clothes " +
            "Тогда: СК200, вещь создана, вернулся обьект вещи")

    @Test
    void testCreateClothes() {

        ClothesResponse createdClothes = createClothes(getClothesCreationBody());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(createdClothes)
                            .withFailMessage("Вещь не создалась")
                            .isNotNull();
                }
        );

        deleteClothesById(createdClothes.getId());
    }

    @DisplayName("Дано:  " +
            "Когда: GET /clothes " +
            "Тогда: СК200, вернулся список всех вещей")

    @Test
    void testGetAllClothes() {

        List<ClothesResponse> clothesResponses = getAllClothes();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(clothesResponses.size())
                            .withFailMessage("Не вернулся список всех вещей")
                            .isNotNull();
                }
        );

    }

    @DisplayName("Дано: Валидный id созданной вещи  " +
            "Когда: DELETE /clothes/{id} " +
            "Тогда: СК200, вещь удалена")

    @Test
    void testDeleteClothesById() {

        ClothesResponse createdClothes = createClothes(getClothesCreationBody());

        deleteClothesById(createdClothes.getId());

    }

    @DisplayName("Дано: Валидный id созданной вещи " +
            "Когда: GET /clothes/{id} " +
            "Тогда: СК200, вернулся список всех вещей")

    @Test
    void testGetClothesById() {

        ClothesResponse createdClothes = createClothes(getClothesCreationBody());

        ClothesResponse gettedClothes = getClothesById(createdClothes.getId());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(createdClothes.getId())
                            .withFailMessage("Не вернулся список всех вещей")
                            .isEqualTo(gettedClothes.getId());
                }
        );
        deleteClothesById(createdClothes.getId());
    }

    @DisplayName("Дано: Валидный статус для вещеё " +
            "Когда: GET /clothes/clothes-in-status/{status} " +
            "Тогда: СК200, вернулся список всех вещей в подходящем статусе")

    @Test
    void testGetClothesInStatus() {

        ClothesResponse createdClothes = createClothes(getClothesCreationBody());

        List<ClothesResponse> clothesInStatus = getClothesInStatus("1");

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(clothesInStatus.get(0).getStatus())
                            .withFailMessage("Не вернулся список всех вещей")
                            .isEqualTo(1);
                }
        );
    }


}
