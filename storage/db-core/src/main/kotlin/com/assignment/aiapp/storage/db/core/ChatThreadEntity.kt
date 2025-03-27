package com.assignment.aiapp.storage.db.core

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table


@Table(
    name = "chat_threads",
    indexes = [
        Index(name = "ix_chat_threads__userid_createdat", columnList = "user_id, created_at desc"),
        Index(name = "ix_chat_threads__createdat", columnList = "created_at desc")
    ]
)
@Entity
class ChatThreadEntity(
    @Column(name = "user_id", nullable = false, updatable = false)
    val userId: Long,
): BaseEntity() {
}