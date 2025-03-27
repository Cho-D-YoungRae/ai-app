package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

private const val CHAT_PAGE_SIZE = 10

@Component
class ChatReader(
    private val clock: Clock,
    private val chatRepository: ChatRepository,
) {

    fun getThread(user: User): ChatThread {
        val thread = chatRepository.findLatestThread(user) ?: chatRepository.createThread(user)
        val thirtyMinutesAgo = LocalDateTime.now(clock).minusMinutes(30)
        return if (thread.lastMessage()?.chatAt?.isBefore(thirtyMinutesAgo) == true) {
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

    fun getThreads(page: Int, sort: Sort): List<ChatThread> {
        return chatRepository.findThreads(page, CHAT_PAGE_SIZE, sort)
    }

    fun getThreads(user: User, page: Int, sort: Sort): List<ChatThread> {
        return chatRepository.findThreads(user, page, CHAT_PAGE_SIZE, sort)
    }
}