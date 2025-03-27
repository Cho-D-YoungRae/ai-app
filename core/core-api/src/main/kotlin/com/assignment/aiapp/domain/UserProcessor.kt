package com.assignment.aiapp.domain

import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class UserProcessor(
    private val userRepository: UserRepository
) {

    fun signup(userSignup: UserSignup) {
        if (userRepository.find(userSignup.email) != null) {
            throw CoreException(ErrorType.USER_ALREADY_EXISTS, "email=${userSignup.email}")
        }
        userRepository.add(userSignup)
    }
}