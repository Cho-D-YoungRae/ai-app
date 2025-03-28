package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.core.enums.FeedbackType
import com.assignment.aiapp.core.enums.UserRole
import com.assignment.aiapp.domain.Sort
import com.assignment.aiapp.domain.chat.ChatReader
import com.assignment.aiapp.domain.user.UserReader
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Service

@Service
class FeedbackService(
    private val userReader: UserReader,
    private val chatReader: ChatReader,
    private val feedbackProcessor: FeedbackProcessor,
    private val feedbackReader: FeedbackReader,
) {

    fun feedback(messageId: Long, userId: Long, type: FeedbackType) {
        val user = userReader.get(userId)
        val chatMessage = chatReader.getMessage(messageId)
        feedbackProcessor.feedback(user, chatMessage, type)
    }

    fun getList(userId: Long, sort: Sort, page: Int): List<Feedback> {
        val user = userReader.get(userId)
        return if (UserRole.ADMIN == user.role) {
            feedbackReader.getFeedbacks(page, sort)
        } else {
            feedbackReader.getFeedbacks(user, page, sort)
        }
    }

    fun update(userId: Long, feedbackId: Long, update: FeedbackUpdate) {
        val user = userReader.get(userId)
        if (user.role != UserRole.ADMIN) {
            throw CoreException(ErrorType.FEEDBACK_UPDATE_FORBIDDEN, "userId=$userId")
        }
        feedbackProcessor.update(feedbackId, update)
    }
}