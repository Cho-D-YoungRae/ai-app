package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.client.openai.MessageRequest
import com.assignment.aiapp.client.openai.OpenAiClient
import com.assignment.aiapp.core.enums.ChatMessageRole
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class ChatProcessor(
    private val clock: Clock,
    private val chatRepository: ChatRepository,
    private val openAiClient: OpenAiClient

) {

    fun chat(model: String, thread: ChatThread, newMessage: NewChatMessage): ChatMessage {
        val messages = thread.messages.toMutableList()
        messages.add(chatRepository.addMessage(thread, newMessage))

        return chatRepository.addMessage(thread, NewChatMessage(
            content = openAiClient.chat(model, toRequestList(messages)),
            role = ChatMessageRole.ASSISTANT,
            chatAt = LocalDateTime.now(clock)
        ))
    }

    private fun toRequestList(messages: List<ChatMessage>): List<MessageRequest> {
        return messages.map {
            MessageRequest(
                role = it.role,
                content = it.content
            )
        }
    }

    fun deleteThread(thread: ChatThread) {
        chatRepository.delete(thread)
    }
}