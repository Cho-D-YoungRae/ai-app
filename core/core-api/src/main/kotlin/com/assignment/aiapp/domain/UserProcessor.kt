package com.assignment.aiapp.domain

import org.springframework.stereotype.Component

@Component
class UserProcessor(
    private val userRepository: UserRepository
) {

    fun signup(userSignup: UserSignup) {
        userRepository.add(userSignup)
    }
}