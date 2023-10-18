package com.repill.was.operation.repository;

import com.repill.was.operation.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressInfoJpaRepository extends JpaRepository<AddressInfo, Long> {

    List<AddressInfo> findAll();

    Optional<AddressInfo> findByAddressDetailA(String addressDetailA);

    Optional<AddressInfo> findByAddressDetailB(String addressDetailB);

    Optional<AddressInfo> findByAddressDetailAAndAddressDetailB(String addressDetailA, String addressDetailB);
}
