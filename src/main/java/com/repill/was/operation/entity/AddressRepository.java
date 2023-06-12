package com.repill.was.operation.entity;

public interface AddressRepository {
    AddressId nextId();

    Address save(Address member);
}
