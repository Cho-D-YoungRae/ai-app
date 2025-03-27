package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.v1.request.ChatRequest
import com.assignment.aiapp.api.controller.v1.response.ChatResponse
import com.assignment.aiapp.api.support.LoginUserEmail
import com.assignment.aiapp.domain.chat.ChatService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/chat")
    fun chat(@LoginUserEmail userEmail: String, @RequestBody @Validated request: ChatRequest): ChatResponse {
        val message = chatService.chat(userEmail, request.model, request.content)
        return ChatResponse(
            content = message.content,
            chatAt = message.chatAt
        )
    }
}