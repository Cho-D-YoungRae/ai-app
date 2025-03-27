package com.assignment.aiapp.api.config

import com.assignment.aiapp.api.support.LoginUserEmailArgumentResolver
import com.assignment.aiapp.domain.user.JwtTokenProvider
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val jwtTokenProvider: JwtTokenProvider
): WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(LoginUserEmailArgumentResolver(jwtTokenProvider))
    }
}