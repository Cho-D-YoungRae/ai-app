package com.assignment.aiapp.storage.db.core

import com.assignment.aiapp.core.enums.FeedbackStatus
import com.assignment.aiapp.core.enums.FeedbackType
import jakarta.persistence.*

@Table(
    name = "feedback",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_feedback__chatmessageid_userid", columnNames = ["chat_message_id", "user_id"])
    ],
    indexes = [
        Index(name = "ix_feedback__createdat", columnList = "created_at desc"),
        Index(name = "ix_feedback__userid_createdat", columnList = "user_id, created_at desc"),
    ]
)
@Entity
class FeedbackEntity(
    @Column(name = "user_id", nullable = false, updatable = false)
    val userId: Long,
    @Column(name = "chat_message_id", nullable = false, updatable = false)
    val chatMessageId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 25, nullable = false, updatable = false)
    val type: FeedbackType,
): BaseEntity() {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 25, nullable = false)
    var status: FeedbackStatus = FeedbackStatus.PENDING
}