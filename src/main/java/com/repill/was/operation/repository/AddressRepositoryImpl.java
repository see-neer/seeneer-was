package com.repill.was.operation.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.operation.entity.Address;
import com.repill.was.operation.entity.AddressId;
import com.repill.was.operation.entity.AddressRepository;
import com.repill.was.operation.repository.jpa.AddressJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final SequenceGenerator sequenceGenerator;
    private final AddressJpaRepository addressJpaRepository;

    @Override
    public AddressId nextId() {
        return new AddressId(sequenceGenerator.generate());
    }

    @Override
    public Address save(Address address) {
        return addressJpaRepository.save(address);
    }
}
