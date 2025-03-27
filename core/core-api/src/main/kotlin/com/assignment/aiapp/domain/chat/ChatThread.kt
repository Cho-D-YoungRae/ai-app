package com.assignment.aiapp.domain.chat

import java.time.LocalDateTime

data class ChatThread(
    val id: Long,
    val createdAt: LocalDateTime
)
