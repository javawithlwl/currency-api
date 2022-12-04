package com.lwl.currency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "currency_details")
@Getter
@Setter
public class CurrencyDetails extends BaseEntity {

    @Id
    private UUID id;
    private String name;
    private String code;
    private String symbol;
    private int decimalPlaces;

    @Override
    @PrePersist
    void onPersists() {
        this.id = UUID.randomUUID();
    }
}
