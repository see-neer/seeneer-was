package com.repill.was.comment.entity;

import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;

import java.util.Optional;

public interface CommentRepository {

    CommentId nextId();

    Comment save(Comment member);

    void delete(Comment member);

    Optional<Comment> findById(CommentId commentId);
}
