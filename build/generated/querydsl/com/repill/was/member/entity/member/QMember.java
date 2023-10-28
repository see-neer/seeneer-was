package com.repill.was.member.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 602523677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.repill.was.member.entity.account.QAccountId accountId;

    public final QAddress address;

    public final StringPath ageRange = createString("ageRange");

    public final EnumPath<com.repill.was.global.enums.AuthType> authType = createEnum("authType", com.repill.was.global.enums.AuthType.class);

    public final DateTimePath<java.time.LocalDateTime> bannedAt = createDateTime("bannedAt", java.time.LocalDateTime.class);

    public final StringPath birthday = createString("birthday");

    public final StringPath birthdayType = createString("birthdayType");

    public final DateTimePath<java.time.LocalDateTime> closedAt = createDateTime("closedAt", java.time.LocalDateTime.class);

    public final StringPath connectedAt = createString("connectedAt");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ListPath<FavoriteItem, QFavoriteItem> favoriteItems = this.<FavoriteItem, QFavoriteItem>createList("favoriteItems", FavoriteItem.class, QFavoriteItem.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final QMemberId id;

    public final StringPath imageSrc = createString("imageSrc");

    public final ListPath<Address, QAddress> interestingAddress = this.<Address, QAddress>createList("interestingAddress", Address.class, QAddress.class, PathInits.DIRECT2);

    public final SimplePath<com.repill.was.global.model.ImageListData> interestingCategory = createSimple("interestingCategory", com.repill.was.global.model.ImageListData.class);

    public final NumberPath<Long> kakaoUserId = createNumber("kakaoUserId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final ListPath<RecentlyViewedItem, QRecentlyViewedItem> recentlyViewedItems = this.<RecentlyViewedItem, QRecentlyViewedItem>createList("recentlyViewedItems", RecentlyViewedItem.class, QRecentlyViewedItem.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountId = inits.isInitialized("accountId") ? new com.repill.was.member.entity.account.QAccountId(forProperty("accountId")) : null;
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.id = inits.isInitialized("id") ? new QMemberId(forProperty("id")) : null;
    }

}

