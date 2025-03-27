package com.assignment.aiapp.api.support

import com.assignment.aiapp.domain.user.AccessToken
import com.assignment.aiapp.domain.user.UserTokenDecoder
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
    private val userTokenDecoder: UserTokenDecoder
): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(LoginUserEmail::class.java)
        val hasType = (String::class.java == parameter.parameterType)
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
        val token = AccessToken(authorizationHeader.substring(BEARER_PREFIX.length))
        val userEmail = userTokenDecoder.decode(token)
        return userEmail
    }
}