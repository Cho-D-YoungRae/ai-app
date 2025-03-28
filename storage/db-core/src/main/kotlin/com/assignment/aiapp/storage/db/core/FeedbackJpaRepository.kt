package com.assignment.aiapp.storage.db.core

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackJpaRepository : JpaRepository<FeedbackEntity, Long> {

    fun findByUserIdAndChatMessageId(userId: Long, chatMessageId: Long): FeedbackEntity?

    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): List<FeedbackEntity>

    fun findAllByOrderByCreatedAtAsc(pageable: Pageable): List<FeedbackEntity>

    fun findAllByUserIdOrderByCreatedAtDesc(userId: Long, pageable: Pageable): List<FeedbackEntity>

    fun findAllByUserIdOrderByCreatedAtAsc(userId: Long, pageable: Pageable): List<FeedbackEntity>
}