package com.example.androidpractice.ui.common

/**
 * 새로고침 할 경우 이것을 가져다가 오버라이드 하겠다.
 * xml에 바인딩해서 사용하고, 다시 Fragment에서 오버라이딩
 */
interface RetryCallback {
    fun retry()
}
