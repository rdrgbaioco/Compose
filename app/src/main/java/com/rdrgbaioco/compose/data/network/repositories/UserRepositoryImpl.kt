package com.rdrgbaioco.compose.data.network.repositories

import com.rdrgbaioco.compose.domain.entities.User
import com.rdrgbaioco.compose.domain.repositories.UserRepository
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {

    override suspend fun getAll(): List<User> {
        // Simulate network delay (2 seconds)
        delay(2000)
        return listOf(
            User(
                id = 1,
                name = "John Doe",
                email = "jhon.doe@example.com"
            ),
            User(
                id = 2,
                name = "Mark Smith",
                email = "mark.smith@example.com"
            ),
            User(
                id = 3,
                name = "Sara Jones",
                email = "sara.jones@example.com"
            ),
        )
    }
}