package com.repill.was.member.entity.memberfollower;

public interface MemberFollowerRepository {

    MemberFollowerId nextId();

    MemberFollower save(MemberFollower memberFollower);
}
