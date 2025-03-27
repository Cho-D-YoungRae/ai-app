package com.assignment.aiapp.api.controller.v1.response

import java.time.LocalDateTime

data class NewChatResponse(
    val content: String,
    val chatAt: LocalDateTime
)
