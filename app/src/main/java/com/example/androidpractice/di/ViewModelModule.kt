package com.example.androidpractice.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidpractice.ui.search.SearchViewModel
import com.example.androidpractice.viewmodel.AndroidViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused") // 이곳에서 발생하는 위험 메세지 생략
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AndroidViewModelFactory): ViewModelProvider.Factory

}