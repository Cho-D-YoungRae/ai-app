package com.assignment.aiapp.domain.chat

import java.time.LocalDateTime

data class ChatThread(
    val id: Long,
    val userId: Long,
    val createdAt: LocalDateTime,
    val messages: List<ChatMessage>,
) {

    fun lastMessage(): ChatMessage? {
        return messages.lastOrNull()
    }
}
