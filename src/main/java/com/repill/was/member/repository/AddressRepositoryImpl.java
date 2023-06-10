package com.repill.was.member.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.*;
import com.repill.was.member.repository.jpa.AddressJpaRepository;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
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
