package com.assignment.aiapp.api.error

import com.assignment.aiapp.support.error.CoreException
import com.assignment.aiapp.support.error.ErrorKind
import com.assignment.aiapp.support.error.ErrorType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CoreException::class)
    fun handleCoreException(ex: CoreException): ResponseEntity<ErrorResponse> {
        val errorType: ErrorType = ex.errorType
        when (errorType.logLevel) {
            LogLevel.ERROR -> log.error("${CoreException::class.simpleName} : {}", ex.message, ex)
            LogLevel.WARN -> log.warn("${CoreException::class.simpleName} : {}", ex.message, ex)
            else -> log.info("${CoreException::class.simpleName} : {}", ex.message, ex)
        }
        val status = getHttpStatusCode(errorType.kind)
        val response = ErrorResponse(errorType)
        return ResponseEntity
            .status(status)
            .body(response)
    }

    private fun getHttpStatusCode(kind: ErrorKind): HttpStatus {
        return when (kind) {
            ErrorKind.SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR
            ErrorKind.UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE
            ErrorKind.VALIDATION_FAILED, ErrorKind.ILLEGAL_STATE -> HttpStatus.BAD_REQUEST
            ErrorKind.NOT_FOUND -> HttpStatus.NOT_FOUND
            ErrorKind.DUPLICATED -> HttpStatus.CONFLICT
            ErrorKind.FORBIDDEN -> HttpStatus.FORBIDDEN
        }
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error("Exception : {}", ex.message, ex)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(ErrorType.DEFAULT_ERROR))
    }
}