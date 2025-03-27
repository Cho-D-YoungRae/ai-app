package com.assignment.aiapp.domain

import com.assignment.aiapp.core.enums.UserRole

data class User(
    val email: String,
    val name: String,
    val role: UserRole,
)
