package com.assignment.aiapp.domain.feedback

import com.assignment.aiapp.core.enums.FeedbackType
import com.assignment.aiapp.core.enums.UserRole
import com.assignment.aiapp.domain.chat.ChatMessage
import com.assignment.aiapp.domain.chat.ChatRepository
import com.assignment.aiapp.domain.user.User
import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class FeedbackProcessor(
    private val chatRepository: ChatRepository,
    private val feedbackRepository: FeedbackRepository,
) {

    fun feedback(user: User, chatMessage: ChatMessage, type: FeedbackType) {
        if (feedbackRepository.find(user, chatMessage) != null) {
            throw CoreException(ErrorType.FEEDBACK_ALREADY_EXISTS,
                "userId=${user.id}, messageId=${chatMessage.id}")
        }

        if (UserRole.MEMBER == user.role && chatRepository.getUserId(chatMessage) != user.id ) {
            throw CoreException(ErrorType.FEEDBACK_CREATE_FORBIDDEN,
                "userId=${user.id}, messageId=${chatMessage.id}")
        }

        feedbackRepository.add(user, chatMessage, type)
    }
}