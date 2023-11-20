package com.repill.was.member;

import com.repill.was.global.enums.ItemType;
import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import com.repill.was.member.service.MemberLikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberLikeServiceTests {

    @Mock
    private MemberLikeRepository memberLikeRepository;

    @InjectMocks
    private MemberLikeService memberLikeService;

    @Test
    public void 사용자가_좋아요를_누른_이력이_없을때() {
        MemberLikeCommand memberLikeCommand = createMemberLikeCommand();

        memberLikeService.deleteLike(memberLikeCommand);

        verify(memberLikeRepository , times(1)).findByItemIdAndMemberId(memberLikeCommand.getLikeType().name(),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId());
    }

    @Test
    public void 사용자가_좋아요를_누른_이력이_있을때() {
        MemberLikeCommand memberLikeCommand = createMemberLikeCommand();
        Optional<MemberLike> memberLike = Optional.of(MemberLike.newOne(
                new MemberLikeId(2L),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId(),
                memberLikeCommand.getLikeType()
        ));

        when(memberLikeRepository.findByItemIdAndMemberId(memberLikeCommand.getLikeType().name()
                , memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId())).thenReturn(memberLike);

        memberLikeService.deleteLike(memberLikeCommand);

        verify(memberLikeRepository , times(1)).findByItemIdAndMemberId(memberLikeCommand.getLikeType().name(),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId());

        verify(memberLikeRepository , times(1)).delete(memberLike.get());

    }

    private MemberLikeCommand createMemberLikeCommand() {
        MemberId requestMemberId = new MemberId(1L);
        String itemType = ItemType.FESTIVAL.name();
        Long itemId = 10L;
        return MemberLikeCommand.request(requestMemberId, itemType, itemId);
    }
}

