package com.rdrgbaioco.compose.di

import com.rdrgbaioco.compose.data.network.repositories.UserRepositoryImpl
import com.rdrgbaioco.compose.domain.repositories.UserRepository
import com.rdrgbaioco.compose.ui.screen.user.viewmodel.UserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleRepositories = module {
    factory<UserRepository> { UserRepositoryImpl() }
}

val moduleViewModels = module {
    singleOf(::UserViewModel)
}

val appModules = module {
    includes(moduleViewModels, moduleRepositories)
}