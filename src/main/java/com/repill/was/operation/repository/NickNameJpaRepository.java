package com.repill.was.operation.repository;

import com.repill.was.operation.entity.NickName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NickNameJpaRepository extends JpaRepository<NickName, Long> {
}
