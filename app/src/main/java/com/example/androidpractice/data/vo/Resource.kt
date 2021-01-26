package com.example.androidpractice.data.vo

import com.example.androidpractice.data.vo.Status.SUCCESS
import com.example.androidpractice.data.vo.Status.ERROR
import com.example.androidpractice.data.vo.Status.LOADING


/**
 * Data에 상태까지 감싸 liveData로 만들어서 상태에 따른 동작을 할수 있게 한다.
 * 예를 들어 progress 처리
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
