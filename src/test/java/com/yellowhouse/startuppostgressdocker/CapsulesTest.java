package com.yellowhouse.startuppostgressdocker;

import com.yellowhouse.startuppostgressdocker.models.CapsulesResponse;
import com.yellowhouse.startuppostgressdocker.models.ClothesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.yellowhouse.startuppostgressdocker.steps.CapsulesSteps.*;
import static com.yellowhouse.startuppostgressdocker.steps.ClothesSteps.createClothes;
import static com.yellowhouse.startuppostgressdocker.steps.ClothesSteps.getClothesById;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getCapsuleCreationBody;
import static com.yellowhouse.startuppostgressdocker.utils.TestObjectBuilder.getClothesCreationBody;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest
class CapsulesTest {

    @DisplayName("Дано: Корректное тело запроса " +
            "Когда: POST /capsules " +
            "Тогда: СК200, капсула создана, вернулся обьект капсулы")

    @Test
    void testCreateCapsule() {

        CapsulesResponse createdCapsula = createCapsule(getCapsuleCreationBody());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(createdCapsula)
                            .withFailMessage("Капсула не создалась")
                            .isNotNull();
                }
        );

        deleteCapsuleById(createdCapsula.getId());
    }

    @DisplayName("Дано: " +
            "Когда: GET /capsules " +
            "Тогда: СК200, получены все капсулы")

    @Test
    void testGetAllCapsules() {
        List<CapsulesResponse> capsulesResponseList = getAllCapsules();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(capsulesResponseList.isEmpty())
                            .withFailMessage("Не вернулся список всех капсул")
                            .isFalse();
                }
        );
    }

    @DisplayName("Дано: Валидный id созданной капсулы " +
            "Когда: GET /capsules/{id} " +
            "Тогда: СК200, получена нужная капсула")

    @Test
    void testCapsuleById() {

        CapsulesResponse createdCapsula = createCapsule(getCapsuleCreationBody());

        CapsulesResponse gettedCapsula = getCapsuleById(createdCapsula.getId());
        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(gettedCapsula.getId())
                            .withFailMessage("Вернулась не та капсула")
                            .isEqualTo(createdCapsula.getId());
                }
        );

        deleteCapsuleById(createdCapsula.getId());
    }

    @DisplayName("Дано: Валидный id созданной капсулы " +
            "Когда: DELETE /capsules/{id} " +
            "Тогда: СК200, удалена капсула")

    @Test
    void testDeleteCapsuleById() {

        CapsulesResponse createdCapsula = createCapsule(getCapsuleCreationBody());


        deleteCapsuleById(createdCapsula.getId());

    }

    @DisplayName("Дано: Валидный id созданной капсулы и id созданной вещи " +
            "Когда: GET /capsules/add-clothes-to-capsule" +
            "Тогда: СК200, вещь добавлена в капсулу")

    @Test
    void testPutClothesInCapsula() {
        CapsulesResponse createdCapsula = createCapsule(getCapsuleCreationBody());

        ClothesResponse createdClothes = createClothes(getClothesCreationBody());

        putClothesInCapsula(createdCapsula.getId(), createdClothes.getId());

        CapsulesResponse updatedCapsula = getCapsuleById(createdCapsula.getId());

        ClothesResponse updatedClothes = getClothesById(createdClothes.getId());

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(updatedCapsula.getClothesInCapsulaIds().size())
                            .withFailMessage("Вещь не добавилась в капсулу")
                            .isEqualTo(1);
                    softAssertions
                            .assertThat(updatedClothes.isInCapsula())
                            .withFailMessage("Флаг вещи не поменялся")
                            .isTrue();
                }
        );


    }
}
