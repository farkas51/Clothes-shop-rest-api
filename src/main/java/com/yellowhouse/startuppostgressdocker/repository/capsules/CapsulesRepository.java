package com.yellowhouse.startuppostgressdocker.repository.capsules;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CapsulesRepository extends JpaRepository<Capsule, UUID> {

    List<Capsule> findByClothesInCapsula_id(UUID clothesId);

    List<Capsule> getBySizeAndType(int size, String type);
}
