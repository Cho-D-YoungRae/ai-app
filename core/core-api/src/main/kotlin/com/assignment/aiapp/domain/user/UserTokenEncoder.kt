package com.assignment.aiapp.domain.user

import org.springframework.stereotype.Component

@Component
class UserTokenEncoder {

    fun encode(user: User): AccessToken {
        return AccessToken("jwt-${user.email}")
    }
}