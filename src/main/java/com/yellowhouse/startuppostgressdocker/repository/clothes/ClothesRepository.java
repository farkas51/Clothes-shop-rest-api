package com.yellowhouse.startuppostgressdocker.repository.clothes;

import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, UUID> {

    List<Clothes> getByStatus(Integer status);

    List<Clothes> findByCapsules_id(UUID capsuleId);
}
