package com.assignment.aiapp.domain

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userProcessor: UserProcessor,
    private val userTokenEncoder: UserTokenEncoder,
) {

    fun signup(userSignup: UserSignup) {
        userProcessor.signup(userSignup)
    }

    fun login(userLogin: UserLogin): AccessToken {
        val user = userProcessor.login(userLogin)
        return userTokenEncoder.encode(user)
    }
}