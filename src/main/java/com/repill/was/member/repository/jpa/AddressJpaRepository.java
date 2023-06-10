package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.Address;
import com.repill.was.member.entity.AddressId;
import com.repill.was.member.entity.Member;
import com.repill.was.member.entity.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, AddressId> {
}
