package com.repill.was.global.factory.likevalidate;

import com.repill.was.comment.entity.Comment;
import com.repill.was.comment.entity.CommentId;
import com.repill.was.comment.entity.CommentNotFoundException;
import com.repill.was.comment.entity.CommentRepository;
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
public class CommentLikeValidator implements LikeValidator {
    private final CommentRepository commentRepository;
    @Override
    public LikeType getSupportType() {
        return LikeType.COMMENT;
    }


    @Override
    public void addLike(Long itemId, MemberLike newMemberLike) {
        Comment comment = commentRepository.findById(new CommentId(itemId)).orElseThrow(CommentNotFoundException::new);
        comment.addLike(newMemberLike);
        commentRepository.save(comment);
    }
}
