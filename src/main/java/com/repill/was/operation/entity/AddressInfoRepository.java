package com.repill.was.operation.entity;

import java.util.List;
import java.util.Optional;

public interface AddressInfoRepository {

    List<AddressInfo> findAll();
    List<AddressInfo> findByAddressDetailA(String addressDetailA);
    List<AddressInfo> findByAddressDetailB(String addressDetailB);
    Optional<AddressInfo> findByAddressDetailAAndAddressDetailB(String addressDetailA, String addressDetailB);
}
