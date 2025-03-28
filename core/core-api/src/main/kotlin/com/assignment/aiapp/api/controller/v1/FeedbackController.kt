package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.response.ListResponse
import com.assignment.aiapp.api.controller.v1.request.FeedbackRequest
import com.assignment.aiapp.api.controller.v1.request.PageRequest
import com.assignment.aiapp.api.controller.v1.response.FeedbackResponse
import com.assignment.aiapp.api.support.LoginUserId
import com.assignment.aiapp.domain.feedback.FeedbackService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class FeedbackController(
    private val feedbackService: FeedbackService
) {

    @PostMapping("/feedback")
    fun feedback(
        @RequestBody @Validated request: FeedbackRequest,
        @LoginUserId userId: Long
    ) {
        feedbackService.feedback(
            messageId = request.messageId,
            userId = userId,
            type = request.type
        )
    }

    @GetMapping("/feedbacks")
    fun getList(
        @LoginUserId userId: Long,
        @ModelAttribute @Validated pageRequest: PageRequest
    ): ListResponse<FeedbackResponse> {
        return ListResponse(
            feedbackService.getList(
                userId = userId,
                page = pageRequest.page,
                sort = pageRequest.sort
            ).map {
                FeedbackResponse(
                    userId = it.userId,
                    chatMessageId = it.chatMessageId,
                    type = it.type,
                    createdAt = it.createdAt,
                    status = it.status
                )
            }
        )
    }
}