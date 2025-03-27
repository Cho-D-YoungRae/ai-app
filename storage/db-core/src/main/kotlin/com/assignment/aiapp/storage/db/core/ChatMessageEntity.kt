package com.assignment.aiapp.storage.db.core

import com.assignment.aiapp.core.enums.ChatMessageRole
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(
    name = "chat_message",
    indexes = [
        Index(name = "ix_chat_message__threadid_chatat", columnList = "thread_id, chat_at desc")
    ]
)
@Entity
class ChatMessageEntity(
    @Column(name = "thread_id", nullable = false, updatable = false)
    val threadId: Long,
    @Column(name = "content", length = 4000, nullable = false, updatable = false)
    val content: String,
    @Column(name = "role", length = 25, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    val role: ChatMessageRole,
    @Column(name = "chat_at", nullable = false, updatable = false)
    val chatAt: LocalDateTime
): BaseEntity() {
}