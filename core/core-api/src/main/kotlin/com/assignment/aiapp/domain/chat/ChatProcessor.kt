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
    private val chatReader: ChatReader,
    private val chatRepository: ChatRepository,
    private val openAiClient: OpenAiClient

) {

    fun chat(model: String, thread: ChatThread, message: ChatMessage): ChatMessage {
        val messages = chatReader.getMessages(thread).toMutableList()
        messages.add(message)
        chatRepository.addMessage(thread, message)
        val answer = ChatMessage(
            content = openAiClient.chat(model, toRequestList(messages)),
            role = ChatMessageRole.ASSISTANT,
            chatAt = LocalDateTime.now(clock)
        )
        chatRepository.addMessage(thread, answer)
        return answer
    }

    private fun toRequestList(messages: List<ChatMessage>): List<MessageRequest> {
        return messages.map {
            MessageRequest(
                role = it.role,
                content = it.content
            )
        }
    }
}