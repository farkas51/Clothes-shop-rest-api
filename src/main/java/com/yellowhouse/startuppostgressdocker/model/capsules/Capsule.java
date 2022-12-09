package com.yellowhouse.startuppostgressdocker.model.capsules;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yellowhouse.startuppostgressdocker.model.clothes.Clothes;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString(exclude = "clothesInCapsula")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "capsules")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Capsule extends CapsuleAudit {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue
    private UUID id;
    private String type;
    private int price;
    private int size;
    private String clothesSize;
    @ManyToMany
    @JoinTable(name = "HAS",
            joinColumns = @JoinColumn(name = "CAPSULES_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "CLOTHES_ID", referencedColumnName = "id")
    )
    private Set<Clothes> clothesInCapsula = new HashSet<>();

    public void addClothesToCapsule(Clothes clothes) {
        clothesInCapsula.add(clothes);
    }
}
