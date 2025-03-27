package com.assignment.aiapp.api.controller.v1.request

import com.assignment.aiapp.domain.UserSignup
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserSignupRequest(
    @field:NotBlank
    @field:Size(max = 200)
    @field:Email
    val email: String,
    @field:NotBlank
    @field:Size(max = 50)
    val password: String,
    @field:NotBlank
    @field:Size(max = 20)
    val name: String,
) {

    fun toUserSignup(): UserSignup {
        return UserSignup(
            email = email,
            password = password,
            name = name
        )
    }

}
