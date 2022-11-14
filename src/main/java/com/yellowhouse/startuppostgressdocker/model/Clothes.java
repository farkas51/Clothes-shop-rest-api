package com.yellowhouse.startuppostgressdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="clothes")
public class Clothes extends ClothesAudit {

    @Id
    @Column(name="ROWID")
    private UUID id;

    private String type;
    private String name;
    private int size;
    private int price;
    private boolean inCapsula;
    private int wear;
    private List<Ids> capsulesIds;
    private int status;
    private int coolKoef;
}
