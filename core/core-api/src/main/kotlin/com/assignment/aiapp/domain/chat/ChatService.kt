package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.core.enums.ChatMessageRole
import com.assignment.aiapp.core.enums.UserRole
import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.user.UserReader
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
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

    fun chat(userId: Long, model: String, messageContent: String): ChatMessage {
        val newMessage = NewChatMessage(
            content = messageContent,
            role = ChatMessageRole.USER,
            chatAt = LocalDateTime.now(clock)
        )
        val thread = chatReader.getThread(userReader.get(userId))
        return chatProcessor.chat(model, thread, newMessage)
    }

    fun getConversations(userId: Long, page: Int, sort: Sort): List<Conversation> {
        val user = userReader.get(userId)
        return if (UserRole.ADMIN == user.role) {
            chatReader.getChatList(page, sort)
        } else {
            chatReader.getChatList(user, page, sort)
        }
    }

    fun deleteThread(userId: Long, threadId: Long) {
        val thread = chatReader.getThread(threadId)
        val user = userReader.get(userId)
        if (thread.userId != user.id) {
            throw CoreException(ErrorType.CHAT_THREAD_DELETE_FORBIDDEN, "threadId=$threadId, userId=${user.id}")
        }

        chatProcessor.deleteThread(thread)
    }
}