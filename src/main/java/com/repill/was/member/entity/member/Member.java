package com.repill.was.member.entity.member;

import com.repill.was.global.enums.AuthType;
import com.repill.was.global.model.ImageListData;
import com.repill.was.global.model.ImageListDataConverter;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.MarketId;
import com.repill.was.member.controller.dto.request.MemberAddInformationRequest;
import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<FavoriteItem> favoriteItems = new ArrayList<>();


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_follower",
            joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_follower_idx")
    private List<MemberFollower> memberFollowers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_recently_viewed_item",
            joinColumns = @JoinColumn(name = "member_id"))
    @OrderColumn(name = "member_recently_viewed_item_idx")
    private List<RecentlyViewedItem> recentlyViewedItems = new ArrayList<>();

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressDetailA",
            column = @Column(name = "interesting_addressDetailA")),
            @AttributeOverride(name = "addressDetailB",
                    column = @Column(name = "interesting_addressDetailB")),
            @AttributeOverride(name = "addressDetailC",
                    column = @Column(name = "interesting_addressDetailC")),
            @AttributeOverride(name = "addressDetailD",
                    column = @Column(name = "interesting_addressDetailD"))
    })
    private Address interestingAddress;

    @Convert(converter = ImageListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private ImageListData interestingCategory;

    @Column(columnDefinition = "TEXT")
    String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Enumerated(EnumType.STRING)
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

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime bannedAt;
    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    protected Member(MemberId id, AccountId accountId, List<FavoriteItem> favoriteItems, List<MemberFollower> memberFollowers, List<RecentlyViewedItem> recentlyViewedItems, Address address, Address interestingAddress, ImageListData interestingCategory, String imageSrc, String nickname, AuthType authType, LocalDateTime closedAt, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt, LocalDateTime bannedAt, LocalDateTime updatedAt, LocalDateTime createdAt) {
    }

    public Member(MemberId id, AccountId accountId, String imageSrc, String nickname, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt) {
       // todo 현재는 카카오 로그인 밖에 없음. 다른 방법이 추가되면 고도화 필요
        createKaKaoMember(id,
                accountId,
                imageSrc,
                nickname,
                kakaoUserId,
                ageRange,
                birthday,
                birthdayType,
                gender,
                connectedAt);
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

    public void deleteFavoriteItem(FavoriteItem favoriteItem) {
        List<FavoriteItem> originalFavoriteItem = this.favoriteItems;
        this.favoriteItems = originalFavoriteItem
                .stream()
                .filter(one -> !one.equals(favoriteItem))
                .collect(Collectors.toList());
    }

    public void deleteRecentlyViewedItem(RecentlyViewedItem recentlyViewedItem) {
        List<RecentlyViewedItem> originalRecentlyViewedItem = this.recentlyViewedItems;
        this.recentlyViewedItems =
                originalRecentlyViewedItem
                        .stream()
                        .filter(one -> !one.equals(recentlyViewedItem))
                        .collect(Collectors.toList());
    }

    public static Member createNotBannedMember() {
        return new Member(null);
    }
    private void createKaKaoMember(MemberId memberId,
                                    AccountId accountId,
                                    String imageSrc,
                                    String nickname,
                                    Long kakaoUserId,
                                    String ageRange,
                                    String birthday,
                                    String birthdayType,
                                    String gender,
                                    String connectedAt) {
        this.id = memberId;
        this.accountId = accountId;
        this.favoriteItems = null;
        this.memberFollowers = null;
        this.recentlyViewedItems = null;
        this.address = null;
        this.imageSrc = imageSrc;
        this.nickname = nickname;
        this.authType = AuthType.KAKAO;
        this.closedAt = null;
        this.kakaoUserId = kakaoUserId;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.birthdayType = birthdayType;
        this.gender = gender;
        this.connectedAt = connectedAt;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(MemberAddInformationRequest.MemberAddress myAddressInfo,
                                  ImageListData interestingCategoryList,
                                  MemberAddInformationRequest.MemberAddress interestingAddress) {
        this.address = Address.newOne(myAddressInfo.getAddressDetailA(), myAddressInfo.getAddressDetailB(), myAddressInfo.getAddressDetailC(), myAddressInfo.getAddressDetailD());
        this.interestingCategory = interestingCategoryList;
        this.interestingAddress = Address.newOne(interestingAddress.getAddressDetailA(), interestingAddress.getAddressDetailB(), interestingAddress.getAddressDetailC(), interestingAddress.getAddressDetailD());
    }

    public void updateMyProfile(String nickname, String imageSrc) {
        this.nickname = nickname;
        this.imageSrc = imageSrc;
    }

    public void markAsClosed() {
        this.closedAt = LocalDateTime.now();
    }
}
