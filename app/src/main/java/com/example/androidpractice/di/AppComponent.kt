package com.example.androidpractice.di

import android.app.Application
import com.example.androidpractice.AndroidPracticeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, // activity나 fragment 자동 주입 장치
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent : AndroidInjector<AndroidPracticeApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: AndroidPracticeApp): AppComponent
    }
}