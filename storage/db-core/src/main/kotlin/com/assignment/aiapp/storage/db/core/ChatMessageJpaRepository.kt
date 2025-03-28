package com.assignment.aiapp.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageJpaRepository: JpaRepository<ChatMessageEntity, Long> {

    fun findAllByThreadIdOrderByCreatedAtDesc(threadId: Long): List<ChatMessageEntity>

    fun findAllByThreadIdIn(threadIds: Collection<Long>): List<ChatMessageEntity>
}