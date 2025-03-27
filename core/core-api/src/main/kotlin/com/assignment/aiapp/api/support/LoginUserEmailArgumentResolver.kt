package com.assignment.aiapp.api.support

import com.assignment.aiapp.domain.user.JwtTokenProvider
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

private const val BEARER_PREFIX = "Bearer "

class LoginUserEmailArgumentResolver(
    private val jwtTokenProvider: JwtTokenProvider
): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(LoginUserId::class.java)
        val hasType = (Long::class.java == parameter.parameterType)
        return hasAnnotation && hasType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authorizationHeader = webRequest.getHeader(AUTHORIZATION)
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw CoreException(ErrorType.USER_AUTHENTICATION_FAILED, "Authorization header is missing or invalid")
        }
        val token = authorizationHeader.substring(BEARER_PREFIX.length)
        return jwtTokenProvider.decode(token)
    }
}