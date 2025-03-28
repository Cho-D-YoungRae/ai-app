package com.assignment.aiapp.domain.user

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

    fun login(userLogin: UserLogin): User {
        return userRepository.find(userLogin.email, userLogin.password)
            ?: throw CoreException(
                ErrorType.USER_AUTHENTICATION_FAILED,
                "email=${userLogin.email}, password=${userLogin.password}"
            )
    }
}