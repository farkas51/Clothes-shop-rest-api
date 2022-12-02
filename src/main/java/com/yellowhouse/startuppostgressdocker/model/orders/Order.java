package com.yellowhouse.startuppostgressdocker.model.orders;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order extends OrderAudit {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue
    private UUID id;

    private UUID userId;
    private UUID capsuleId;
    private int price;
    private int size;
    private LocalDateTime deliveryDateToClient;
    private LocalDateTime deliveryDateBack;


}
