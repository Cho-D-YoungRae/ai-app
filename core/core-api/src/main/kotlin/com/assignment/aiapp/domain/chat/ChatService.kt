package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.core.enums.ChatMessageRole
import com.assignment.aiapp.domain.user.UserReader
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

@Service
class ChatService(
    private val clock: Clock,
    private val userReader: UserReader,
    private val chatReader: ChatReader,
    private val chatProcessor: ChatProcessor,
) {

    fun chat(userEmail: String, model: String, messageContent: String): ChatMessage {
        val message = ChatMessage(
            content = messageContent,
            role = ChatMessageRole.USER,
            chatAt = LocalDateTime.now(clock)
        )
        val thread = chatReader.getThread(userReader.get(userEmail))
        return chatProcessor.chat(model, thread, message)
    }
}