package com.repill.was.member.entity.memberfavoritemarket;

public interface MemberFavoriteMarketRepository {

    MemberFavoriteMarketId nextId();

    MemberFavoriteMarket save(MemberFavoriteMarket market);
}
