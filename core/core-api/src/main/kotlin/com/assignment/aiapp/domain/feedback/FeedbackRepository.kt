package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.core.enums.FeedbackType
import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.chat.ChatMessage
import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.storage.db.core.FeedbackEntity
import com.assignment.aiapp.storage.db.core.FeedbackJpaRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class FeedbackRepository(
    private val feedbackJpaRepository: FeedbackJpaRepository
) {

    fun find(user: User, chatMessage: ChatMessage): Feedback? {
        return feedbackJpaRepository.findByUserIdAndChatMessageId(user.id, chatMessage.id)?.let {
            Feedback(
                id = it.id!!,
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

    fun findAll(page: Int, size: Int, sort: Sort): List<Feedback> {
        val pageRequest = PageRequest.of(page, size)
        return if (Sort.DESC == sort) {
            feedbackJpaRepository.findAllByOrderByCreatedAtDesc(
                pageRequest
            )
        } else {
            feedbackJpaRepository.findAllByOrderByCreatedAtAsc(pageRequest)
        }.map {
            Feedback(
                id = it.id!!,
                userId = it.userId,
                chatMessageId = it.chatMessageId,
                type = it.type,
                createdAt = it.createdAt!!,
                status = it.status
            )
        }
    }

    fun findAll(user: User, page: Int, size: Int, sort: Sort): List<Feedback> {
        return if (Sort.DESC == sort) {
            feedbackJpaRepository.findAllByUserIdOrderByCreatedAtDesc(
                user.id,
                PageRequest.of(page, size)
            )
        } else {
            feedbackJpaRepository.findAllByUserIdOrderByCreatedAtAsc(
                user.id,
                PageRequest.of(page, size)
            )
        }.map {
            Feedback(
                id = it.id!!,
                userId = it.userId,
                chatMessageId = it.chatMessageId,
                type = it.type,
                createdAt = it.createdAt!!,
                status = it.status
            )
        }
    }
}
