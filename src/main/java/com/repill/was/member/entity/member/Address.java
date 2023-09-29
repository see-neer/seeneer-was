package com.repill.was.member.entity.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
public class Address {

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

    protected Address() {}

    public Address(String addressCodeA, String addressDetailA, String addressDetailB, String addressDetailC, String addressDetailD) {
        this.addressCodeA = addressCodeA;
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.addressDetailC = addressDetailC;
        this.addressDetailD = addressDetailD;
    }
}
