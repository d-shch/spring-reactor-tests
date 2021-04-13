package com.example.sr

import org.springframework.stereotype.Component

@Component
class UserUtil {

    fun fromEntity(userEntity: UserEntity?): User =
            with(userEntity) {
                User(
                        id = UserId(userEntity?.id!!),
                        firstName = FirstName(userEntity.firstName),
                        lastName = LastName(userEntity.lastName)
                )
            }

    fun toEntity(user: User): UserEntity =
            with(user) {
                UserEntity(
                        id = user.id?.value,
                        firstName = user.firstName.value,
                        lastName = user.lastName.value
                )
            }

    fun toView(userEntity: UserEntity): UserView =
            with(userEntity) {
                UserView(
                        id = userEntity.id.toString(),
                        firstName = userEntity.firstName,
                        lastName = userEntity.lastName
                )
            }

    fun toView(user: User): UserView =
            with(user) {
                UserView(
                        id = user.id.toString(),
                        firstName = user.firstName.value,
                        lastName = user.lastName.value
                )
            }

}