package com.example.sr

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class UserRepositoryHandMade(
        private val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    fun findById(id: Long?): Mono<UserEntity> =
        r2dbcEntityTemplate
                .selectOne(query(where("id")
                        .`is`(id!!)), UserEntity::class.java)

    fun findAll(): Flux<UserEntity> {
        return r2dbcEntityTemplate
                .select(UserEntity::class.java).all()
    }

    fun insert(userEntity: UserEntity): Mono<UserEntity> =
            r2dbcEntityTemplate.insert(userEntity)

}