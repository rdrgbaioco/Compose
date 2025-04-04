package com.rdrgbaioco.compose.ui.screen.user.viewmodel

import com.rdrgbaioco.compose.domain.entities.User

data class UserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
)