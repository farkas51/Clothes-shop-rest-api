package com.yellowhouse.startuppostgressdocker.model.clothes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yellowhouse.startuppostgressdocker.model.capsules.Capsules;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@ToString(exclude = "capsules")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clothes")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Clothes extends ClothesAudit {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue
    private UUID id;

    private String type;
    private String name;
    private int size;
    private int price;
    private boolean inCapsula;
    private int wear;
    private int status;
    private int coolKoef;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clothesInCapsula")
    private Set<Capsules> capsules = new HashSet<>();

}
