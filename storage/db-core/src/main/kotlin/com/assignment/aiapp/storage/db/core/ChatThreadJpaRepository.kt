package com.assignment.aiapp.storage.db.core

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ChatThreadJpaRepository : JpaRepository<ChatThreadEntity, Long> {

    fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): ChatThreadEntity?

    fun findAllByUserIdOrderByCreatedAtDesc(userId: Long, pageable: Pageable): List<ChatThreadEntity>

    fun findAllByUserIdOrderByCreatedAtAsc(userId: Long, pageable: Pageable): List<ChatThreadEntity>

    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): List<ChatThreadEntity>

    fun findAllByOrderByCreatedAtAsc(pageable: Pageable): List<ChatThreadEntity>
}