package com.assignment.aiapp.domain.chat

data class Conversation(
    val thread: ChatThread,
    val messages: List<ChatMessage>,
)
