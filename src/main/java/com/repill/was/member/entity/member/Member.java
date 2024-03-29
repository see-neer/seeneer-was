package com.repill.was.member.entity.member;

import com.repill.was.global.enums.AuthType;
import com.repill.was.global.model.EntityListData;
import com.repill.was.global.model.EntityListDataConverter;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
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
    private List<Address> interestingAddress;

    @Convert(converter = EntityListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private EntityListData interestingCategory;

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

    @Embedded
    private MemberSetting memberSetting;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime bannedAt;
    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    protected Member(MemberId id, AccountId accountId, List<FavoriteItem> favoriteItems, List<RecentlyViewedItem> recentlyViewedItems, Address address, List<Address> interestingAddress, EntityListData interestingCategory, String imageSrc, String nickname, AuthType authType, LocalDateTime closedAt, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt, MemberSetting memberSetting, LocalDateTime bannedAt, LocalDateTime updatedAt, LocalDateTime createdAt) {
    }

    public Member(MemberId id, AccountId accountId, String imageSrc, String nickname, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt) {
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
        originalFavoriteItem.remove(favoriteItem);
        this.favoriteItems = originalFavoriteItem;
    }

    public void deleteRecentlyViewedItem(RecentlyViewedItem recentlyViewedItem) {
        List<RecentlyViewedItem> originalRecentlyViewedItem = this.recentlyViewedItems;
        originalRecentlyViewedItem.remove(recentlyViewedItem);
        this.recentlyViewedItems = originalRecentlyViewedItem;
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
        this.memberSetting = MemberSetting.defaultSetting();
        this.connectedAt = connectedAt;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(String myAddressInfo,
                                  EntityListData interestingCategoryList,
                                  List<String> interestingAddress,
                                  String nickname) {
        this.address = Address.newOne(myAddressInfo, null, null, null);
        this.interestingCategory = interestingCategoryList;
        this.interestingAddress = interestingAddress.stream().map(one -> {return Address.newOne(one, null, null, null);}).collect(Collectors.toList());;
        this.nickname = nickname;
    }

    public void updateMemberSetting(MemberSetting memberSetting) {
        this.memberSetting = memberSetting;
    }


    public void updateMyProfile(String nickname, String imageSrc) {
        this.nickname = nickname;
        this.imageSrc = imageSrc;
    }

    public void markAsClosed() {
        this.closedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();;
    }

    public void markReCreatedMember(AccountId accountId) {
        this.closedAt = null;
        this.accountId = accountId;
        this.updatedAt = LocalDateTime.now();;
    }
}
