package com.assignment.aiapp.api.controller.v1.request

import com.assignment.aiapp.domain.user.UserLogin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserLoginRequest(
    @field:NotBlank
    @field:Size(max = 200)
    @field:Email
    val email: String,
    @field:NotBlank
    @field:Size(max = 50)
    val password: String,
) {

    fun toUserLogin(): UserLogin {
        return UserLogin(
            email = email,
            password = password
        )
    }
}
