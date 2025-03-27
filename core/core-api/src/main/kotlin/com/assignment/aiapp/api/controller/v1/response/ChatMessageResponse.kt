package com.assignment.aiapp.api.controller.v1.response

import com.assignment.aiapp.core.enums.ChatMessageRole
import java.time.LocalDateTime

data class ChatMessageResponse(
    val content: String,
    val role: ChatMessageRole,
    val chatAt: LocalDateTime
)
