package com.assignment.aiapp.domain.user

import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    fun get(email: String): User {
        return userRepository.find(email) ?: throw CoreException(
            ErrorType.USER_NOT_FOUND,
            "사용자를 찾을 수 없습니다. email: $email"
        )
    }
}