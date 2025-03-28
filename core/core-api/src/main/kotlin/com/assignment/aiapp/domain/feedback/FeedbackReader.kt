package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.user.User
import org.springframework.stereotype.Component

private const val FEEDBACK_PAGE_SIZE = 10

@Component
class FeedbackReader(
    private val feedbackRepository: FeedbackRepository,
) {

    fun getFeedbacks(user: User, page: Int, sort: Sort): List<Feedback> {
        return feedbackRepository.findAll(user, page, FEEDBACK_PAGE_SIZE, sort)
    }

    fun getFeedbacks(page: Int, sort: Sort): List<Feedback> {
        return feedbackRepository.findAll(page, FEEDBACK_PAGE_SIZE, sort)
    }
}