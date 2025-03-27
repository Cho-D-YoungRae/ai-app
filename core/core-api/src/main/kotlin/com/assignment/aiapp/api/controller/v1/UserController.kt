package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.v1.request.UserSignupRequest
import com.assignment.aiapp.domain.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Validated request: UserSignupRequest) {
        userService.signup(request.toUserSignup())
    }
}