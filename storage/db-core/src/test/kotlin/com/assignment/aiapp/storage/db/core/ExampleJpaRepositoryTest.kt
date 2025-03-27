package com.assignment.aiapp.storage.db.core

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ExampleJpaRepositoryTest {

    @Autowired
    lateinit var exampleJpaRepository: ExampleJpaRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun save() {
        val exampleEntity = ExampleEntity("example content")
        exampleJpaRepository.save(exampleEntity)
        entityManager.flush()
        entityManager.clear()

        val result = exampleJpaRepository.findById(exampleEntity.id!!).get()
        assertThat(result.content).isEqualTo("example content")

    }
}