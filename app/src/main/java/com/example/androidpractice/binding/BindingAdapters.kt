package com.example.androidpractice.binding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    /**
     * object로 구성된 코드를 자바에서 사용하려면, 해당 내 속성 및 함수가 자바의 필드/메서드로 해석되도록
     * 알려줘야 한다. 이를 위한 것이 JvmStatic이다. 이때, const val 값은 JvmStatic을 붙이지 않아도 된다.
     */

    /**
     * Databinding을 이용해 xml에서 custom동작을 만들수 있게 한다.
     */
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}