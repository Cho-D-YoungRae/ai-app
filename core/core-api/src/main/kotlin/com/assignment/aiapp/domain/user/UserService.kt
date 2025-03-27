package com.assignment.aiapp.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userProcessor: UserProcessor,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun signup(userSignup: UserSignup) {
        userProcessor.signup(userSignup)
    }

    fun login(userLogin: UserLogin): String {
        val user = userProcessor.login(userLogin)
        return jwtTokenProvider.encode(user)
    }
}