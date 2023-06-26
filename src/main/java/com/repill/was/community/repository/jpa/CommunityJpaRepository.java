package com.repill.was.community.repository.jpa;

import com.repill.was.community.entity.Community;
import com.repill.was.community.entity.CommunityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityJpaRepository extends JpaRepository<Community, CommunityId> {
}
