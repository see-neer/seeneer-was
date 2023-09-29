package com.repill.was.community.entity;




public interface CommunityRepository {
    CommunityId nextId();

    Community save(Community community);
}
