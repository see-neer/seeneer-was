package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

public interface MemberFavoriteMarketRepository {

    MemberFavoriteMarketId nextId();

    MemberFavoriteMarket save(MemberFavoriteMarket market);
}
