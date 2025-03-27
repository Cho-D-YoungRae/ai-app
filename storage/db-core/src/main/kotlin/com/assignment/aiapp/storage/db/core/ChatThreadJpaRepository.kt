package com.assignment.aiapp.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface ChatThreadJpaRepository: JpaRepository<ChatThreadEntity, Long> {

    fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): ChatThreadEntity?
}