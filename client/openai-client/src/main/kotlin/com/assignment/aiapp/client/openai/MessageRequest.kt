package com.assignment.aiapp.client.openai

import com.assignment.aiapp.core.enums.ChatMessageRole

data class MessageRequest(
    val content: String,
    val role: ChatMessageRole
)
