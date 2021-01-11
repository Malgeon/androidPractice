package com.example.androidpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ACC 파라미터가 있는 ViewModel을 생성하기 위해선(MVVM의 ViewModel이 아니다!)
 * viewModelProvider.Factory에서 넣어줘야 한다.
 * 이를 dagger에서 singleton으로 다루면서 관리를 해주도록 만든다.
 * ViewModelModule에서 bind를 해줘야 하며, 단순히
 */

@Singleton
class AndroidViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}