package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.response.ListResponse
import com.assignment.aiapp.api.controller.v1.request.NewChatRequest
import com.assignment.aiapp.api.controller.v1.response.ChatMessageResponse
import com.assignment.aiapp.api.controller.v1.response.ChatThreadResponse
import com.assignment.aiapp.api.controller.v1.response.NewChatResponse
import com.assignment.aiapp.api.support.LoginUserId
import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.chat.ChatService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/chat")
    fun chat(@LoginUserId userId: Long, @RequestBody @Validated request: NewChatRequest): NewChatResponse {
        val message = chatService.chat(userId, request.model, request.content)
        return NewChatResponse(
            content = message.content,
            chatAt = message.chatAt
        )
    }

    @GetMapping("/threads")
    fun getConversations(@LoginUserId userId: Long, page: Int, sort: Sort): ListResponse<ChatThreadResponse> {
        val threads = chatService.getThreads(userId, page, sort)
        return ListResponse(threads.map {
            ChatThreadResponse(
                id = it.id,
                userId = it.userId,
                createdAt = it.createdAt,
                messages = it.messages.map { message ->
                    ChatMessageResponse(
                        id = message.id,
                        content = message.content,
                        role = message.role,
                        chatAt = message.chatAt
                    )
                }
            )
        })
    }

    @DeleteMapping("/threads/{threadId}")
    fun deleteThread(
        @LoginUserId userId: Long,
        @PathVariable threadId: Long
    ) {
        chatService.deleteThread(userId, threadId)
    }
}