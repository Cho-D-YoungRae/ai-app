package com.assignment.aiapp.api.controller.v1.request

import com.assignment.aiapp.domain.Sort
import jakarta.validation.constraints.Max

data class PageRequest(
    @Max(value = 1000)
    val page: Int = 0,
    val sort: Sort = Sort.DESC,
)
