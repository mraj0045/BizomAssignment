package com.bizom.claim.di

import com.bizom.claim.di.ui.MainModule
import com.bizom.claim.ui.claim.AddClaimActivity
import com.bizom.claim.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun addClaimActivity(): AddClaimActivity

}
