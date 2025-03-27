package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class ChatReader(
    private val clock: Clock,
    private val chatRepository: ChatRepository,
) {

    fun getThread(user: User): ChatThread {
        val thread = chatRepository.findLatestThraed(user) ?: chatRepository.createThread(user)
        val thirtyMinutesAgo = LocalDateTime.now(clock).minusMinutes(30)
        return if (thread.createdAt.isBefore(thirtyMinutesAgo)) {
            chatRepository.createThread(user)
        } else {
            thread
        }
    }

    fun getThread(threadId: Long): ChatThread {
        return chatRepository.findThread(threadId) ?: throw CoreException(
            ErrorType.CHAT_THREAD_NOT_FOUND,
            "threadId=$threadId"
        )
    }

    fun getMessages(thread: ChatThread): List<ChatMessage> {
        return chatRepository.findMessages(thread)
    }
}