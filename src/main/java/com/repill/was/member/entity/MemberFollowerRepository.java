package com.repill.was.member.entity;

public interface MemberFollowerRepository {

    MemberFollowerId nextId();

    MemberFollower save(MemberFollower memberFollower);
}
