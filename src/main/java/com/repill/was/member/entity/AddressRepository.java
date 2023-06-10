package com.repill.was.member.entity;

public interface AddressRepository {
    AddressId nextId();

    Address save(Address member);
}
