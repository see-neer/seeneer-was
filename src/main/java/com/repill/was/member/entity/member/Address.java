package com.repill.was.member.entity.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
public class Address {

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailA;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailB;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailC;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailD;

    protected Address() {}

    public Address(String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.addressDetailC = addressDetailC;
        this.addressDetailD = addressDetailD;
    }

    public static Address newOne(String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        return new Address(addressDetailA, addressDetailB, addressDetailC, addressDetailD);
    }
}
