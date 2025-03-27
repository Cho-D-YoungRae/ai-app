package com.assignment.aiapp.domain

import org.springframework.stereotype.Component

@Component
class UserTokenDecoder {

    fun decode(token: AccessToken): String {
        return token.value.removePrefix("jwt-")
    }
}