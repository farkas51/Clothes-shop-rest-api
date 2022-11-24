package com.yellowhouse.startuppostgressdocker.repository.capsules;

import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CapsulesRepository extends JpaRepository<Capsules, UUID> {

}
