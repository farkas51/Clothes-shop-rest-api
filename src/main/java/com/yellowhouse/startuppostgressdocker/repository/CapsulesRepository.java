package com.yellowhouse.startuppostgressdocker.repository;

import com.yellowhouse.startuppostgressdocker.model.Capsules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CapsulesRepository extends JpaRepository<Capsules, UUID> {

}
