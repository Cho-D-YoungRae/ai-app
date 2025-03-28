package com.assignment.aiapp.api.controller.v1.response

import com.assignment.aiapp.core.enums.FeedbackStatus
import com.assignment.aiapp.core.enums.FeedbackType
import java.time.LocalDateTime

data class FeedbackResponse(
    val userId: Long,
    val chatMessageId: Long,
    val type: FeedbackType,
    val createdAt: LocalDateTime,
    val status: FeedbackStatus
)
