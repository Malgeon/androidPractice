package com.example.androidpractice.di

import com.example.androidpractice.ui.repo.RepoFragment
import com.example.androidpractice.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment
}