package com.rdrgbaioco.compose.ui.screen.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdrgbaioco.compose.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(UserState())
    val uiState: StateFlow<UserState> = _state.asStateFlow()

    /**
     * Update the loading state of the UI atomically.
     */
    private fun setLoading(value: Boolean) {
        _state.update { current ->
            current.copy(
                isLoading = value
            )
        }
    }

    /**
     * Use a coroutine to get the users from the repository in a background thread.
     */
    fun getUsers() = viewModelScope.launch(Dispatchers.IO) {
        setLoading(true)
        val users = repository.getAll()
        _state.update { current ->
            current.copy(
                users = users
            )
        }
        setLoading(false)
    }
}