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

    fun getChatList(page: Int, sort: Sort): List<Conversation> {
        val threads = chatRepository.findThreads(page, CHAT_PAGE_SIZE, sort)
        val threadIdToMessages = chatRepository.findGroupedMessages(threads)
        return threads.map { thread ->
            Conversation(
                thread = thread,
                messages = threadIdToMessages[thread.id] ?: emptyList(),
            )
        }
    }

    fun getChatList(user: User, page: Int, sort: Sort): List<Conversation> {
        val threads = chatRepository.findThreads(user, page, CHAT_PAGE_SIZE, sort)
        val threadIdToMessages = chatRepository.findGroupedMessages(threads)
        return threads.map { thread ->
            Conversation(
                thread = thread,
                messages = threadIdToMessages[thread.id] ?: emptyList(),
            )
        }
    }
}