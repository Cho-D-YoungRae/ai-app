package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.storage.db.core.ChatMessageEntity
import com.assignment.aiapp.storage.db.core.ChatMessageJpaRepository
import com.assignment.aiapp.storage.db.core.ChatThreadEntity
import com.assignment.aiapp.storage.db.core.ChatThreadJpaRepository
import org.springframework.stereotype.Repository

@Repository
class ChatRepository(
    private val chatThreadJpaRepository: ChatThreadJpaRepository,
    private val chatMessageJpaRepository: ChatMessageJpaRepository,
) {

    fun findLatestThraed(user: User): ChatThread? {
        return chatThreadJpaRepository.findFirstByUserIdOrderByCreatedAtDesc(user.id)
            ?.let {
                ChatThread(
                    id = it.id!!,
                    createdAt = it.createdAt!!,
                )
            }
    }

    fun createThread(user: User): ChatThread {
        val entity = chatThreadJpaRepository.save(
            ChatThreadEntity(
                userId = user.id,
            )
        )
        return ChatThread(
            id = entity.id!!,
            createdAt = entity.createdAt!!,
        )
    }

    fun findMessages(chatThread: ChatThread): List<ChatMessage> {
        return chatMessageJpaRepository.findAllByThreadIdOrderByCreatedAtDesc(chatThread.id).map {
            ChatMessage(
                content = it.content,
                role = it.role,
                chatAt = it.chatAt,
            )
        }
    }

    fun addMessage(thread: ChatThread, message: ChatMessage) {
        chatMessageJpaRepository.save(
            ChatMessageEntity(
                threadId = thread.id,
                content = message.content,
                role = message.role,
                chatAt = message.chatAt,
            )
        )
    }
}