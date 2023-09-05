package com.repill.was.member.facade;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.festival.entity.FestivalRepository;
import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import com.repill.was.market.entity.MarketRepository;
import com.repill.was.member.controller.dto.request.MemberLoginRequest;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.favoriteitems.FavoriteItem;
import com.repill.was.member.entity.favoriteitems.FavoriteItemId;
import com.repill.was.member.entity.favoriteitems.FavoriteItemRepository;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemRepository;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.repository.vo.RecentlyViewedItemInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MarketRepository marketRepository;
    private final RecentlyViewedItemRepository recentlyViewedItemRepository;
    private final MemberQueries memberQueries;
    private final FestivalRepository festivalRepository;
    private final FavoriteItemRepository favoriteItemRepository;

    public Boolean checkDuplicateNickname(String insertedNickname) {
        return memberQueries.findByMemberNickName(insertedNickname).isPresent();
    }

    public List<RecentlyViewedItemResponse> getRecentlyViewList(MemberId memberId, int size, Long cursorId) {
        List<RecentlyViewedItemInfoVO> byMemberId = recentlyViewedItemRepository.findByMemberId(memberId, size, cursorId);
        return byMemberId.stream().map(one -> {
            if (one.getItemType().equals(ItemType.MARKET.name())) {
                Market market = marketRepository.findById(new MarketId(one.getItemId())).orElseThrow(() -> new BadRequestException("존재 하지 않는 마켓 정보 입니다."));
                return RecentlyViewedItemResponse.fromMarket(market, one.getId());
            }

            if (one.getItemType().equals(ItemType.FESTIVAL.name())) {
                Festival festival = festivalRepository.findById(new FestivalId(one.getItemId())).orElseThrow(() -> new BadRequestException("존재 하지 않는 페스티벌 정보 입니다."));
                return RecentlyViewedItemResponse.fromFestival(festival, one.getId());
            }
            throw new BadRequestException("유효하지 않은 아이템 정보 입니다.");
        }).collect(Collectors.toList());
    }

    public void deleteFavoriteItem(FavoriteItemId id, MemberId memberId) {
        FavoriteItem favoriteItem = favoriteItemRepository.findByIdAndAccountId(id, memberId).orElseThrow(() -> new BadRequestException("존재하지 않는 최근 본 목록 값 입니다."));
        favoriteItemRepository.delete(favoriteItem);
    }

    public List<RecentlyViewedItemResponse> getFavoriteItems(MemberId memberId, int size, Long cursorId) {
        List<RecentlyViewedItemInfoVO> byMemberId = favoriteItemRepository.findByMemberId(memberId, size, cursorId);
        return byMemberId.stream().map(one -> {
            if (one.getItemType().equals(ItemType.MARKET.name())) {
                Market market = marketRepository.findById(new MarketId(one.getItemId())).orElseThrow(() -> new BadRequestException("존재 하지 않는 마켓 정보 입니다."));
                return RecentlyViewedItemResponse.fromMarket(market, one.getId());
            }

            if (one.getItemType().equals(ItemType.FESTIVAL.name())) {
                Festival festival = festivalRepository.findById(new FestivalId(one.getItemId())).orElseThrow(() -> new BadRequestException("존재 하지 않는 페스티벌 정보 입니다."));
                return RecentlyViewedItemResponse.fromFestival(festival, one.getId());
            }
            throw new BadRequestException("유효하지 않은 아이템 정보 입니다.");
        }).collect(Collectors.toList());
    }

    public void deleteRecentlyView(RecentlyViewedItemId id, MemberId memberId) {
        RecentlyViewedItem recentlyViewedItem = recentlyViewedItemRepository.findByIdAndAccountId(id, memberId).orElseThrow(() -> new BadRequestException("존재하지 않는 최근 본 목록 값 입니다."));
        recentlyViewedItemRepository.delete(recentlyViewedItem);
    }
}
