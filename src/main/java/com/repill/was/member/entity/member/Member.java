package com.repill.was.member.entity.member;

import com.repill.was.global.enums.AuthType;
import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
public class Member {

    @EmbeddedId
    private MemberId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "accountId", nullable = false))
    private AccountId accountId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_favorite_items",
    joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_favorite_items_idx")
    private List<FavoriteItem> favoriteItems;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_follower",
            joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_follower_idx")
    private List<MemberFollower> memberFollowers;

    @ElementCollection(fetch = FetchType.LAZY)
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

    @Column(columnDefinition = "VARCHAR(50)")
    private AuthType authType;
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
    private LocalDateTime bannedAt;
    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;


    public Member(MemberId id, AccountId accountId, List<FavoriteItem> favoriteItems, List<MemberFollower> memberFollowers, List<RecentlyViewedItem> recentlyViewedItems, Address address, String imageSrc, String nickname, AuthType authType, LocalDateTime closedAt, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.favoriteItems = favoriteItems;
        this.memberFollowers = memberFollowers;
        this.recentlyViewedItems = recentlyViewedItems;
        this.address = address;
        this.imageSrc = imageSrc;
        this.nickname = nickname;
        this.authType = authType;
        this.closedAt = closedAt;
        this.kakaoUserId = kakaoUserId;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.birthdayType = birthdayType;
        this.gender = gender;
        this.connectedAt = connectedAt;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Member(LocalDateTime bannedAt) {
        this.bannedAt = bannedAt;
    }

    public void addMemberFollowers(MemberFollower memberFollower) {
        List<MemberFollower> originalMemberFollower = this.memberFollowers;
        originalMemberFollower.add(memberFollower);
        this.memberFollowers = originalMemberFollower;
    }

    public void addRecentlyViewedItems(RecentlyViewedItem recentlyViewedItem) {
        List<RecentlyViewedItem> originalRecentlyViewedItem = this.recentlyViewedItems;
        originalRecentlyViewedItem.add(recentlyViewedItem);
        this.recentlyViewedItems = originalRecentlyViewedItem;
    }
    public void addFavoriteItem(FavoriteItem favoriteItem) {
        List<FavoriteItem> originalFavoriteItem = this.favoriteItems;
        originalFavoriteItem.add(favoriteItem);
        this.favoriteItems = originalFavoriteItem;
    }

    public Boolean checkBannedMember() {
        if(this.bannedAt != null) {
            return true;
        }
        return false;
    }

    public static Member createNotBannedMember() {
        return new Member(null);
    }
    public static Member createKaKaoMember(MemberId memberId,
                                    AccountId accountId,
                                    String imageSrc,
                                    String nickname,
                                    Long kakaoUserId,
                                    String ageRange,
                                    String birthday,
                                    String birthdayType,
                                    String gender,
                                    String connectedAt) {
        return new Member(
                memberId,
                accountId,
                null,
                null,
                null,
                null,
                imageSrc,
                nickname,
                AuthType.KAKAO,
                null,
                kakaoUserId,
                ageRange,
                birthday,
                birthdayType,
                gender,
                connectedAt,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
