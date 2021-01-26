package com.example.androidpractice.ui.common

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.androidpractice.AppExecutors

/**
 * AsyncListDiffer를 간편하게 사용하게 해주며, RecyclerView.Adapter 인터페이스에 필요한 작업을
 * 구현해놔서 바로 사용할수 있게 해준다.
 * 또한 제네릭으로 확장해서 사용할수 있도록 구성한다.
 */

abstract class DataBoundListAdapter<T, V : ViewDataBinding>(
    appExecutors: AppExecutors,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBoundViewHolder<V>>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .setBackgroundThreadExecutor(appExecutors.diskIO())
        .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        // 제네릭으로 받았으니 따로 수행을 해줘야 한다.
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
        // 아래는 item의 변경이 옵져빙이 되면 그 즉시 바꾸도록 해준다.
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup): V
    protected abstract fun bind(binding: V, item: T)
}