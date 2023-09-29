package com.repill.was.member.entity.member;

import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @EmbeddedId
    private MemberId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "accountId", nullable = false))
    private AccountId accountId;

    @ElementCollection
    @CollectionTable(name = "member_favorite_items",
    joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_favorite_items_idx")
    private List<FavoriteItem> favoriteItems;

    @ElementCollection
    @CollectionTable(name = "member_follower",
            joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_follower_idx")
    private List<MemberFollower> memberFollowers;

    @ElementCollection
    @CollectionTable(name = "member_recently_viewed_item",
            joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_recently_viewed_item_idx")
    private List<RecentlyViewedItem> recentlyViewedItems;

    @Embedded
    private Address address;

    @Column(columnDefinition = "TEXT")
    String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime closedAt; // 탈퇴 사용자인지 flag

    @Column(nullable = false, unique = true)
    private Long kakaoUserId; // Kakao 사용자의 고유 ID (또는 다른 적절한 유일한 식별자)

    @Column(columnDefinition = "VARCHAR(50)")
    private String ageRange;
    @Column(columnDefinition = "VARCHAR(50)")
    private String birthday;
    @Column(columnDefinition = "VARCHAR(50)")
    private String birthdayType;
    @Column(columnDefinition = "VARCHAR(50)")
    private String gender;
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String connectedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;
    
    public Member(MemberId memberId, String imageSrc, String nickname) {
        this.id = memberId;
        this.imageSrc = imageSrc;
        this.nickname = nickname;
    }
}
