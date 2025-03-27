package com.assignment.aiapp.support.error

class CoreException: RuntimeException {

    val errorType: ErrorType

    constructor(message: String) : super(message) {
        this.errorType = ErrorType.DEFAULT_ERROR
    }

    constructor(errorType: ErrorType, message: String) : super("$errorType : $message") {
        this.errorType = errorType
    }

    constructor(message: String, cause: Throwable, errorType: ErrorType) : super("$errorType : $message", cause) {
        this.errorType = errorType
    }

}