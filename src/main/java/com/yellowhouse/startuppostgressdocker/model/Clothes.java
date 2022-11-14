package com.yellowhouse.startuppostgressdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clothes")
public class Clothes extends ClothesAudit {

    @Id
    @Column(name="ROWID",unique=true,nullable=false)
    @GeneratedValue
    private UUID id;
    private String type;
    private String name;
    private int size;
    private int price;
    private boolean inCapsula;
    private int wear;
    private UUID capsulesIds;
    private int status;
    private int coolKoef;
}
