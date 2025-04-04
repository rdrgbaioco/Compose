package com.rdrgbaioco.compose.ui.screen.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rdrgbaioco.compose.domain.entities.User
import com.rdrgbaioco.compose.navigation.Screens
import com.rdrgbaioco.compose.ui.screen.user.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navigate: (route: String) -> Unit,
    viewDetails: (user: User) -> Unit,
    viewModel: UserViewModel = koinViewModel()
) {
    /// Get the state from the ViewModel and collect it as a StateFlow
    /// This will automatically update the UI when the state changes
    val uiState by viewModel.uiState.collectAsState()
    val hasFetched = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!hasFetched.value) {
            viewModel.getUsers()
            hasFetched.value = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Users Screen")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigate(Screens.Home.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = uiState.isLoading
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
            UserListView(
                users = uiState.users,
                viewDetails = viewDetails
            )
        }
    }
}


@Composable
fun UserListView(
    users: List<User>,
    viewDetails: (user: User) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 32.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Users")
        }
        items(users) { user ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewDetails(user)
                }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = user.name)
                    Text(text = user.email)
                }
            }
        }
    }
}

