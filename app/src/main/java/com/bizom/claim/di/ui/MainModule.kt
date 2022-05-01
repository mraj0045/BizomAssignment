package com.bizom.claim.di.ui

import androidx.lifecycle.ViewModel
import com.bizom.claim.di.vm.ViewModelKey
import com.bizom.claim.ui.main.MainRepository
import com.bizom.claim.ui.main.MainRepositoryImpl
import com.bizom.claim.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repository: MainRepositoryImpl): MainRepository
}