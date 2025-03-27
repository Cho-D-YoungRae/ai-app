package com.assignment.aiapp.storage.db.core

import com.assignment.aiapp.core.enums.UserRole
import jakarta.persistence.*

@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_users__email", columnNames = ["email"])
    ]
)
@Entity
class UserEntity(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "name", length = 45, nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 25, nullable = false)
    val role: UserRole = UserRole.MEMBER
): BaseEntity() {
}