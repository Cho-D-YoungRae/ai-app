package com.assignment.aiapp.domain.user

import com.assignment.aiapp.core.enums.UserRole

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val role: UserRole,
)
