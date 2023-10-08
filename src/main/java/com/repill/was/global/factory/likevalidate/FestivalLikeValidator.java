package com.repill.was.global.factory.likevalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.FestivalNotFoundException;
import com.repill.was.item.entity.FestivalRepository;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLike.LikeType;
import com.repill.was.review.query.ReviewQueries;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FestivalLikeValidator implements LikeValidator {
    private final ReviewQueries reviewQueries;
    private final FestivalRepository festivalRepository;
    @Override
    public LikeType getSupportType() {
        return LikeType.FESTIVAL;
    }

    @Override
    public void addLike(Long itemId, MemberLike newMemberLike) {
        Festival festival = festivalRepository.findById(new FestivalId(itemId)).orElseThrow(FestivalNotFoundException::new);
        festival.addLike(newMemberLike);
        festivalRepository.save(festival);
    }
}
