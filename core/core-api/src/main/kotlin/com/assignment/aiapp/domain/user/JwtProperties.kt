package com.assignment.aiapp.domain.user

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.jwt")
data class JwtProperties(
    val secretKey: String,
    val expiration: Long,
)
