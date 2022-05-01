package com.bizom.claim.di

import android.content.Context
import com.bizom.claim.App
import com.bizom.claim.di.vm.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBuilderModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        fun context(@BindsInstance applicationContext: Context): Builder

    }

}