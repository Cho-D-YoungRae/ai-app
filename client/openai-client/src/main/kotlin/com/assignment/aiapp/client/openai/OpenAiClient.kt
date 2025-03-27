package com.assignment.aiapp.client.openai

import com.assignment.aiapp.core.enums.ChatMessageRole
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.stereotype.Component

@Component
class OpenAiClient(
    chatClientBuilder: ChatClient.Builder
) {

    private val chatClient = chatClientBuilder.build()

    fun chat(messages: List<MessageRequest>, model: String): String {
        val prompt = Prompt(
            toMessages(messages),
            OpenAiChatOptions.builder().model(model).build()
        )
        return chatClient.prompt(prompt).call().content()!!
    }

    fun toMessages(messages: List<MessageRequest>): List<Message> {
        return messages.map { message ->
            if (message.role == ChatMessageRole.USER) {
                UserMessage(message.content)
            } else {
                AssistantMessage(message.content)
            }
        }
    }
}