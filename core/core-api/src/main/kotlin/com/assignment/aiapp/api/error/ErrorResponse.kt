package com.assignment.aiapp.api.error

import com.assignment.aiapp.support.error.ErrorType

data class ErrorResponse private constructor(
    val type: ErrorType,
    val message: String
) {

    constructor(type: ErrorType) : this(
        type = type,
        message = type.message
    )


}
