package com.example.applyexecutortoservice.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoomUserEntity is a Querydsl query type for ChatRoomUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoomUserEntity extends EntityPathBase<ChatRoomUserEntity> {

    private static final long serialVersionUID = 1144795530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoomUserEntity chatRoomUserEntity = new QChatRoomUserEntity("chatRoomUserEntity");

    public final QChatRoomEntity chatRoom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity user;

    public QChatRoomUserEntity(String variable) {
        this(ChatRoomUserEntity.class, forVariable(variable), INITS);
    }

    public QChatRoomUserEntity(Path<? extends ChatRoomUserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoomUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoomUserEntity(PathMetadata metadata, PathInits inits) {
        this(ChatRoomUserEntity.class, metadata, inits);
    }

    public QChatRoomUserEntity(Class<? extends ChatRoomUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoomEntity(forProperty("chatRoom")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

