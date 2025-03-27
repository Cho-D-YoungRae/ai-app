package com.assignment.aiapp.api.controller.v1.request

import com.assignment.aiapp.core.enums.FeedbackType

data class FeedbackRequest(
    val messageId: Long,
    val type: FeedbackType
)
