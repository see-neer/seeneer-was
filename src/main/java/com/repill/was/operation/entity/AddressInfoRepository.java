package com.repill.was.operation.entity;

import java.util.List;
import java.util.Optional;

public interface AddressInfoRepository {

    List<AddressInfo> findAll();
    Optional<AddressInfo> findByAddressDetailA(String addressDetailA);
    Optional<AddressInfo> findByAddressDetailB(String addressDetailB);
    Optional<AddressInfo> findByAddressDetailAAndAddressDetailB(String addressDetailA, String addressDetailB);
}
