package com.assignment.aiapp.domain.user

import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    fun get(id: Long): User {
        return userRepository.find(id) ?: throw CoreException(
            ErrorType.USER_NOT_FOUND,
            "사용자를 찾을 수 없습니다. id: $id"
        )
    }
}