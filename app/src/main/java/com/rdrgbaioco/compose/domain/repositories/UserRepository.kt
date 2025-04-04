package com.rdrgbaioco.compose.domain.repositories

import com.rdrgbaioco.compose.domain.entities.User

interface UserRepository {

    /**
     * Simulate loading users.
     */
    suspend fun getAll(): List<User>
}