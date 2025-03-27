package com.assignment.aiapp.domain

import com.assignment.aiapp.storage.db.core.UserEntity
import com.assignment.aiapp.storage.db.core.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val userJpaRepository: UserJpaRepository
) {

    fun add(userSignup: UserSignup) {
        userJpaRepository.save(
            UserEntity(
                email = userSignup.email,
                password = userSignup.password,
                name = userSignup.name
            )
        )
    }

    fun find(email: String): User? {
        return userJpaRepository.findByEmail(email)?.let {
            User(
                email = it.email,
                name = it.name,
                role = it.role
            )
        }
    }
}