package com.example.androidpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ACC 파라미터가 있는 ViewModel을 생성하기 위해선(MVVM의 ViewModel이 아니다!)
 * viewModelProvider.Factory에서 넣어줘야 한다. (default시 생략하여 by viewModel이 자동으로 해주지만.)
 * 이제 이 작업을 DI로 해보자.
 * 그러기 위해서 주입해 주는 Factory를 만들어 줘야 한다. 그리고 이 Factory에서 주입을 해주므로 여기서 viewmodel의 예외처리까지 겸해줄수 있다.
 * 그러기 위해서 Mutibinding을 해주는 creators를 Inject로 가지고 온다. 그래서 get() as T로 return 하는 것
 * 사실.. Dagger를 제작하는 팀이 알아야 할 내용인거 같다. 아마 Dagger가 안드로이드에 최적화가 안되어 있기 때문에 필요한 작업으로 생각한다.
 * 이게 koin과 비교해 Dagger의 가장 큰 단점이며, DaggerHilt가 나온 이유이기도 하는것 같다. (물론 잘안다면 커스터 마이징이 가능하단 점에서 장점이기도 하지만... 과연 장점일지)
 */

@Singleton
class AndroidViewModelFactory @Inject constructor(
    // ViewModel DI에서 IntoMap에 의한 MultiBinding작업 (get()작업을) 예외처리까지 겸해주도록 일부러 가져옴.
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