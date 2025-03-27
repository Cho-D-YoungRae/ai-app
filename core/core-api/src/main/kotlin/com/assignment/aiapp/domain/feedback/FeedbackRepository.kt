package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.core.enums.FeedbackType
import com.assignment.aiapp.domain.chat.ChatMessage
import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.storage.db.core.FeedbackEntity
import com.assignment.aiapp.storage.db.core.FeedbackJpaRepository
import org.springframework.stereotype.Repository

@Repository
class FeedbackRepository(
    private val feedbackJpaRepository: FeedbackJpaRepository
) {

    fun find(user: User, chatMessage: ChatMessage): Feedback? {
        return feedbackJpaRepository.findByUserIdAndChatMessageId(user.id, chatMessage.id)?.let {
            Feedback(
                userId = it.userId,
                chatMessageId = it.chatMessageId,
                type = it.type,
                createdAt = it.createdAt!!,
                status = it.status
            )
        }
    }

    fun add(user: User, chatMessage: ChatMessage, type: FeedbackType) {
        feedbackJpaRepository.save(
            FeedbackEntity(
                userId = user.id,
                chatMessageId = chatMessage.id,
                type = type,
            )
        )
    }
}
