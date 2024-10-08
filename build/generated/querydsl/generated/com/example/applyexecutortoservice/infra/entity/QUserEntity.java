package com.example.applyexecutortoservice.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1274176041L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final ListPath<ChatRoomUserEntity, QChatRoomUserEntity> chatRoomUsers = this.<ChatRoomUserEntity, QChatRoomUserEntity>createList("chatRoomUsers", ChatRoomUserEntity.class, QChatRoomUserEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath nickname = createString("nickname");

    public final StringPath uid = createString("uid");

    public final EnumPath<com.example.applyexecutortoservice.domain.user.enums.UserRole> userRole = createEnum("userRole", com.example.applyexecutortoservice.domain.user.enums.UserRole.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

