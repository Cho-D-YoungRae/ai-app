package com.assignment.aiapp.api.controller.v1.request

import com.assignment.aiapp.core.enums.FeedbackStatus
import com.assignment.aiapp.domain.feedback.FeedbackUpdate

data class FeedbackUpdateRequest(
    val status: FeedbackStatus
) {
    fun toFeedbackUpdate(): FeedbackUpdate {
        return FeedbackUpdate(
            status = status
        )
    }
}
