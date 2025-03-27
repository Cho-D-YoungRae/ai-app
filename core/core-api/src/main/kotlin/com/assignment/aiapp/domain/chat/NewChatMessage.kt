package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.core.enums.ChatMessageRole
import java.time.LocalDateTime

data class NewChatMessage(
    val content: String,
    val role: ChatMessageRole,
    val chatAt: LocalDateTime
)
