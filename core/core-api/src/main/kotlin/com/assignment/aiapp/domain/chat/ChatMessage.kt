package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.core.enums.ChatMessageRole
import java.time.LocalDateTime

data class ChatMessage(
    val content: String,
    val role: ChatMessageRole,
    val chatAt: LocalDateTime
)
