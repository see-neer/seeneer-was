package com.repill.was.global.factory.likevalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLike.LikeType;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;

import java.util.List;

public interface LikeValidator {
    LikeType getSupportType();
    void addLike(Long itemId, MemberLike newMemberLike);
}
