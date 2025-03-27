package com.assignment.aiapp.api.controller.response

data class ListResponse<T>(
    val content: List<T>,
)