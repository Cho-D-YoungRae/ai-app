package com.assignment.aiapp.domain.user

import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

@Component
@EnableConfigurationProperties(JwtProperties::class)
class JwtTokenProvider(
    jwtProperties: JwtProperties,
    private val clock: Clock
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray())
    private val expiration: Long = jwtProperties.expiration

    fun encode(user: User): String {
        val now = Instant.now(clock)
        return Jwts.builder()
            .subject(user.id.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(expiration)))
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    fun decode(token: String): Long {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
                .toLong()
        } catch (exception: JwtException) {
            throw CoreException(ErrorType.USER_AUTHENTICATION_FAILED, "JWT 토큰이 유효하지 않습니다. token=$token", exception)
        }
    }
}