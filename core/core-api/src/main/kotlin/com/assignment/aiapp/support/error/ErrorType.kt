package com.assignment.aiapp.support.error

import org.springframework.boot.logging.LogLevel

enum class ErrorType(
    val kind: ErrorKind,
    val message: String,
    val logLevel: LogLevel,
) {

    DEFAULT_ERROR(ErrorKind.SERVER_ERROR, "서버에 문제가 발생했습니다.", LogLevel.ERROR),
    EXTERNAL_ERROR(ErrorKind.SERVER_ERROR, "외부 서버에 문제가 발생했습니다.", LogLevel.ERROR),

    USER_ALREADY_EXISTS(ErrorKind.DUPLICATED, "이미 존재하는 사용자입니다.", LogLevel.INFO),
    USER_AUTHENTICATION_FAILED(ErrorKind.AUTHENTICATION_FAILED, "사용자 인증에 실패했습니다.", LogLevel.INFO),
}