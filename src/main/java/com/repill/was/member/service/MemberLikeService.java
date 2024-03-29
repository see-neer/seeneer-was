package com.repill.was.member.service;

import com.repill.was.global.event.Events;
import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.entity.memberLike.*;
import com.repill.was.member.query.MemberQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MemberLikeService {

    private final static long waitTime = 2;
    private final static long leaseTime = 3;
    private final MemberLikeRepository memberLikeRepository;
    private final MemberQueries memberQueries;
//    private final RedissonClient redissonClient;


    @Transactional
    public void deleteLike(MemberLikeCommand memberLikeCommand) {
        //        RLock lock = redissonClient.getLock(makeMemberLikeLockName(memberLikeCommand.getMemberId().getId(), memberLikeCommand.getItemId(), memberLikeCommand.getLikeType().name()));
//        boolean isLocked = lock.isLocked();
//        if (!isLocked) {
//            try {
//                lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        Optional<MemberLike> byItemIdAndMemberId = memberLikeRepository.findByItemIdAndMemberId(
                memberLikeCommand.getLikeType().name(),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId()
        );

        if(byItemIdAndMemberId.isPresent()) {
            memberLikeRepository.delete(byItemIdAndMemberId.get());
            Events.raise(new MemberUnlikeEvent(memberLikeCommand.getLikeType(), memberLikeCommand.getItemId(), byItemIdAndMemberId.get()));
        }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            } finally {
//                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
//                    lock.unlock();
//                }
//            }
//        }
    }

    @Transactional
    public void addLike(MemberLikeCommand memberLikeCommand) {
//        RLock lock = redissonClient.getLock(makeMemberLikeLockName(memberLikeCommand.getMemberId().getId(), memberLikeCommand.getItemId(), memberLikeCommand.getLikeType().name()));
//        boolean isLocked = lock.isLocked();
//        if (!isLocked) {
//            try {
//                lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
                Optional<MemberLike> byItemIdAndMemberId = memberLikeRepository.findByItemIdAndMemberId(
                        memberLikeCommand.getLikeType().name(),
                        memberLikeCommand.getMemberId(),
                        memberLikeCommand.getItemId()
                );

                if(byItemIdAndMemberId.isEmpty()) {
                    MemberLike newMemberLike = MemberLike.newOne(
                            memberLikeRepository.nextId(),
                            memberLikeCommand.getMemberId(),
                            memberLikeCommand.getItemId(),
                            memberLikeCommand.getLikeType()
                    );
                    memberLikeRepository.save(newMemberLike);
                    Events.raise(new MemberAddedLikeEvent(memberLikeCommand.getLikeType(), memberLikeCommand.getItemId(), newMemberLike));
                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            } finally {
//                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
//                    lock.unlock();
//                }
//            }
//        }
    }

    private String makeMemberLikeLockName(Long memberId, Long itemId, String itemType) {
        return String.format("%s:%s:%s", memberId, itemId, itemType);
    }
}
