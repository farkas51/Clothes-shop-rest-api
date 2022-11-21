package com.yellowhouse.startuppostgressdocker.repository;

import com.yellowhouse.startuppostgressdocker.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, UUID> {

    ArrayList<Clothes> getByNameAndType(String name, String type);
}
