package com.assignment.aiapp.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackJpaRepository: JpaRepository<FeedbackEntity, Long> {

}