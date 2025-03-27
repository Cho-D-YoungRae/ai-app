package com.assignment.aiapp.api.controller.v1.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ChatRequest(
    @field:NotBlank
    @field:Size(max = 3000)
    val content: String,
    @field:NotBlank
    @field:Size(max = 100)
    val model: String
)
