package com.repill.was.operation.repository.jpa;

import com.repill.was.operation.entity.Address;
import com.repill.was.operation.entity.AddressId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, AddressId> {
}
