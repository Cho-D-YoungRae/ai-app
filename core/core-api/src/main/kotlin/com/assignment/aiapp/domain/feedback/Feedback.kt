package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.core.enums.FeedbackStatus
import com.assignment.aiapp.core.enums.FeedbackType
import java.time.LocalDateTime

data class Feedback(
    val id: Long,
    val userId: Long,
    val chatMessageId: Long,
    val type: FeedbackType,
    val createdAt: LocalDateTime,
    val status: FeedbackStatus
) {

    fun updateStatus(status: FeedbackStatus): Feedback {
        return this.copy(status = status)
    }
}
