package com.repill.was.operation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.operation.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.repill.was.operation.entity.QNickName.nickName;

@Repository
@RequiredArgsConstructor
public class NickNameRepositoryImpl implements NickNameRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> findByFirstNickName() {
        return jpaQueryFactory.select(nickName.firstNickName)
                .from(nickName).fetch();
    }

    @Override
    public List<String> findBySecondNickName() {
        return jpaQueryFactory.select(nickName.secondNickName)
                .from(nickName).fetch();
    }
}
