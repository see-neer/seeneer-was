package com.repill.was.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @EmbeddedId
    AddressId addressId;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressCodeA;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailA;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailB;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailC;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailD;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime createdAt;

    public Address(String addressCodeA, String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        this.addressCodeA = addressCodeA;
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.addressDetailC = addressDetailC;
        this.addressDetailD = addressDetailD;
    }

    public static Address newOneForInsert(String addressCodeA, String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        return new Address(
                addressCodeA,
                addressDetailA,
                addressDetailB,
                addressDetailC,
                addressDetailD
        );
    }

    public static Address newOne(AddressId addressId, String addressCodeA, String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        return new Address(
                addressId,
                addressCodeA,
                addressDetailA,
                addressDetailB,
                addressDetailC,
                addressDetailD,
                ZonedDateTime.now()
        );
    }
}
