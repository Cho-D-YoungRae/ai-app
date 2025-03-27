package com.assignment.aiapp.domain

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userProcessor: UserProcessor
) {

    fun signup(userSignup: UserSignup) {
        userProcessor.signup(userSignup)
    }
}