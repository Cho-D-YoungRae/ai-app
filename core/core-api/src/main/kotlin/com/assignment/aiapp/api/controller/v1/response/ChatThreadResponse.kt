package com.assignment.aiapp.api.controller.v1.response

import java.time.LocalDateTime

data class ChatThreadResponse(
    val id: Long,
    val userId: Long,
    val createdAt: LocalDateTime,
    val messages: List<ChatMessageResponse>,
) {

}
