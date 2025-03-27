package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.response.ListResponse
import com.assignment.aiapp.api.controller.v1.request.ChatRequest
import com.assignment.aiapp.api.controller.v1.response.ChatMessageResponse
import com.assignment.aiapp.api.controller.v1.response.ChatResponse
import com.assignment.aiapp.api.controller.v1.response.ConversationResponse
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
    fun chat(@LoginUserId userId: Long, @RequestBody @Validated request: ChatRequest): ChatResponse {
        val message = chatService.chat(userId, request.model, request.content)
        return ChatResponse(
            content = message.content,
            chatAt = message.chatAt
        )
    }

    @GetMapping("/conversations")
    fun getConversations(@LoginUserId userId: Long, page: Int, sort: Sort): ListResponse<ConversationResponse> {
        val conversations = chatService.getConversations(userId, page, sort)
        return ListResponse(conversations.map {
            ConversationResponse(
                threadId = it.thread.id,
                userId = it.thread.userId,
                createdAt = it.thread.createdAt,
                messages = it.messages.map { message ->
                    ChatMessageResponse(
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