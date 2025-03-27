package com.assignment.aiapp.api.controller.v1

import com.assignment.aiapp.api.controller.v1.request.FeedbackRequest
import com.assignment.aiapp.api.support.LoginUserId
import com.assignment.aiapp.domain.feedback.FeedbackService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

}