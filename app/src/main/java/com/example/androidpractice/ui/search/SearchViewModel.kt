package com.example.androidpractice.ui.search

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.androidpractice.data.vo.Repo
import com.example.androidpractice.data.vo.Resource
import com.example.androidpractice.data.vo.Status
import com.example.androidpractice.repository.RepoRepository
import com.example.androidpractice.util.AbsentLiveData
import java.util.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(repoRepository: RepoRepository) : ViewModel() {

    // 서치 해줄 값 (input)
    private val _query = MutableLiveData<String>()
    private val nextPageHandler = NextPageHandler(repoRepository)

    // 서치 하지 못할 경우 layout에서 input 값을 가져와야 해서 둠.
    val query: LiveData<String> = _query

    // 서치 해주도록 한다. 이때, input값이 blank 일 경우 null 값반환 위해 switchMap을 이용
    val results: LiveData<Resource<List<Repo>>> = _query.switchMap { search ->
        if (search.isBlank()) {
            AbsentLiveData.create()
        } else {
            repoRepository.search(search)
        }
    }

    val loadMoreStatus: LiveData<LoadMoreState>
        get() = nextPageHandler.loadMoreState

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
        nextPageHandler.reset()
        _query.value = input
    }

    fun loadNextPage() {
        _query.value?.let {
            if (it.isNotBlank()) {
                nextPageHandler.queryNextPage(it)
            }
        }
    }

    fun refresh() {
        _query.value?.let {
            _query.value = it
        }
    }

    /**
     *  isRunning과 하단 layout의 하단 ProgressBar와 연동하여 true일 경우 보여주도록 한다.
     *  그와 동시에 errorMessage를 가지고 있을 경우 한번 snackbar로 보여주고 null처리
     */

    class LoadMoreState(val isRunning: Boolean, val errorMessage: String?) {
        private var handledError = false
        val errorMessageIfNotHandled: String?
            get() {
                if (handledError) {
                    return null
                }
                handledError = true
                return errorMessage
            }
    }

    /**
     *  nextPage를 가져오고자 할때, 해당 동작이 실행되고 다른 프래그 먼트로 이동하더라도
     *  background에서data를 받는 동작을 하기 위해 observeForever를 사용하려고 한다.
     *  이는 lifecycle이 DESTROY됨과 동시에 remove되는 observe와는 다르게 수동으로 remove를 해줘야 한다.
     *  이를 위해 데이터 캡슐화 및 init 및 remove 전체 동작 관리를 하도록 클래스를 만든다.
     */

    class NextPageHandler(private val repository: RepoRepository) : Observer<Resource<Boolean>> {
        private var nextPageLiveData: LiveData<Resource<Boolean>>? = null

        val loadMoreState = MutableLiveData<LoadMoreState>()

        private var query: String? = null

        /**
         *  nextPage가 더이상 없을 경우 hasMore은 false가 되어 unregister에서 query값이 초기화 되지 않아
         *  queryNextPage가 동작하지 않도록 한다.
         */
        private var _hasMore: Boolean = false
        val hasMore
            get() = _hasMore

        init {
            reset()
        }

        fun queryNextPage(query: String) {
            if (this.query == query) {
                return
            }
            unregister()
            this.query = query
            nextPageLiveData = repository.searchNextPage(query)
            loadMoreState.value = LoadMoreState(
                isRunning = true,
                errorMessage = null
            )
            nextPageLiveData?.observeForever(this)
        }

        override fun onChanged(result: Resource<Boolean>?) {
            if (result == null) {
                reset()
            } else {
                when (result.status) {
                    Status.SUCCESS -> {
                        _hasMore = result.data == true
                        unregister()
                        loadMoreState.setValue(
                            LoadMoreState(
                                isRunning = false,
                                errorMessage = null
                            )
                        )
                    }
                    Status.ERROR -> {
                        _hasMore = true
                        unregister()
                        loadMoreState.setValue(
                            LoadMoreState(
                                isRunning = false,
                                errorMessage = result.message
                            )
                        )

                    }
                    Status.LOADING -> {
                        // ignore
                    }
                }
            }
        }

        private fun unregister() {
            nextPageLiveData?.removeObserver(this)
            nextPageLiveData = null
            if (_hasMore) {
                query = null
            }
        }

        fun reset() {
            unregister()
            _hasMore = true
            loadMoreState.value = LoadMoreState(
                isRunning = false,
                errorMessage = null
            )
        }
    }
}