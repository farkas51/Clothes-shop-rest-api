package com.yellowhouse.startuppostgressdocker.model.orders;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
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
