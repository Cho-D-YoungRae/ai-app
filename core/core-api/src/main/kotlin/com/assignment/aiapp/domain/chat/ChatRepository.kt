package com.assignment.aiapp.domain.chat

import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.storage.db.core.ChatMessageEntity
import com.assignment.aiapp.storage.db.core.ChatMessageJpaRepository
import com.assignment.aiapp.storage.db.core.ChatThreadEntity
import com.assignment.aiapp.storage.db.core.ChatThreadJpaRepository
import com.assignment.aiapp.support.error.CoreException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ChatRepository(
    private val chatThreadJpaRepository: ChatThreadJpaRepository,
    private val chatMessageJpaRepository: ChatMessageJpaRepository,
) {

    fun findLatestThread(user: User): ChatThread? {
        return chatThreadJpaRepository.findFirstByUserIdOrderByCreatedAtDesc(user.id)
            ?.let {
                ChatThread(
                    id = it.id!!,
                    userId = it.userId,
                    createdAt = it.createdAt!!,
                    chatMessageJpaRepository.findAllByThreadIdOrderByChatAtDesc(it.id!!)
                        .map { messageEntity ->
                            ChatMessage(
                                id = messageEntity.id!!,
                                content = messageEntity.content,
                                role = messageEntity.role,
                                chatAt = messageEntity.chatAt,
                            )
                        }
                )
            }
    }

    fun findThread(threadId: Long): ChatThread? {
        return chatThreadJpaRepository.findById(threadId)
            .map {
                ChatThread(
                    id = it.id!!,
                    userId = it.userId,
                    createdAt = it.createdAt!!,
                    chatMessageJpaRepository.findAllByThreadIdOrderByChatAtDesc(it.id!!)
                        .map { messageEntity ->
                            ChatMessage(
                                id = messageEntity.id!!,
                                content = messageEntity.content,
                                role = messageEntity.role,
                                chatAt = messageEntity.chatAt,
                            )
                        }
                )
            }.orElse(null)
    }

    fun createThread(user: User): ChatThread {
        val entity = chatThreadJpaRepository.save(
            ChatThreadEntity(
                userId = user.id,
            )
        )
        return ChatThread(
            id = entity.id!!,
            userId = entity.userId,
            createdAt = entity.createdAt!!,
            messages = emptyList()
        )
    }

    @Transactional
    fun delete(thread: ChatThread) {
        chatThreadJpaRepository.deleteById(thread.id)
        chatMessageJpaRepository.deleteAll(
            chatMessageJpaRepository.findAllByThreadIdOrderByChatAtDesc(thread.id)
        )
    }

    @Transactional
    fun addMessage(thread: ChatThread, message: NewChatMessage): ChatMessage {
        chatThreadJpaRepository.findById(thread.id)
            .orElseThrow { CoreException("Thread not found. threadId=${thread.id}") }
        val entity = chatMessageJpaRepository.save(
            ChatMessageEntity(
                threadId = thread.id,
                content = message.content,
                role = message.role,
                chatAt = message.chatAt,
            )
        )
        return ChatMessage(
            id = entity.id!!,
            content = entity.content,
            role = entity.role,
            chatAt = entity.chatAt,
        )
    }

    fun findThreads(page: Int, size: Int, sort: Sort): List<ChatThread> {
        val threadEntities = if (sort == Sort.ASC) {
            chatThreadJpaRepository.findAllByOrderByCreatedAtAsc(PageRequest.of(page, size))
        } else {
            chatThreadJpaRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size))
        }
        return threadEntitiesToChatThreads(threadEntities)
    }

    fun findThreads(user: User, page: Int, size: Int, sort: Sort): List<ChatThread> {
        val threadEntities = if (sort == Sort.ASC) {
            chatThreadJpaRepository.findAllByUserIdOrderByCreatedAtAsc(user.id, PageRequest.of(page, size))
        } else {
            chatThreadJpaRepository.findAllByUserIdOrderByCreatedAtDesc(user.id, PageRequest.of(page, size))
        }
        return threadEntitiesToChatThreads(threadEntities)
    }

    private fun threadEntitiesToChatThreads(threadEntities: List<ChatThreadEntity>): List<ChatThread> {
        val threadIdToMessageEntities = chatMessageJpaRepository
            .findAllByThreadIdIn(threadEntities.map { it.id!! })
            .groupBy { messageEntity -> messageEntity.threadId }
        return threadEntities.map {
            val messages = threadIdToMessageEntities[it.id]?.map { messageEntity ->
                ChatMessage(
                    id = messageEntity.id!!,
                    content = messageEntity.content,
                    role = messageEntity.role,
                    chatAt = messageEntity.chatAt,
                )
            } ?: emptyList()
            ChatThread(
                id = it.id!!,
                userId = it.userId,
                createdAt = it.createdAt!!,
                messages = messages.sortedBy { message -> message.chatAt }.reversed(),
            )
        }
    }

    fun findMessage(id: Long): ChatMessage? {
        return chatMessageJpaRepository.findById(id)
            .map {
                ChatMessage(
                    id = it.id!!,
                    content = it.content,
                    role = it.role,
                    chatAt = it.chatAt,
                )
            }.orElse(null)
    }

    fun getUserId(chatMessage: ChatMessage): Long {
        val chatMessageEntity = chatMessageJpaRepository.findById(chatMessage.id)
            .orElseThrow { CoreException("Chat message not found. messageId=${chatMessage.id}") }
        return chatThreadJpaRepository.findById(chatMessageEntity.threadId)
            .orElseThrow { CoreException("Chat thread not found. threadId=${chatMessageEntity.threadId}") }
            .userId
    }
}