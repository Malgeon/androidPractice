package com.example.androidpractice.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.androidpractice.AppExecutors
import com.example.androidpractice.R
import com.example.androidpractice.data.vo.Repo
import com.example.androidpractice.databinding.ItemRepoBinding
import com.example.androidpractice.ui.common.DataBoundListAdapter

class RepoListAdapter(
    appExecutors: AppExecutors,
    private val showFullName: Boolean,
    private val repoClickCallback: ((Repo) -> Unit)?
) : DataBoundListAdapter<Repo, ItemRepoBinding> (
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Repo>() {
        // 동일안 아이템 체크, 고유값으로 한다.
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            // primaryKey 값이 동일해야 한다.
            return oldItem.owner == newItem.owner
                    && oldItem.name == newItem.name
        }

        // areItemsTheSame이 true면 동작하는 함수, 이제 content가 맞는지 확인하자.
        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.description == newItem.description
                    && oldItem.stars == newItem.stars
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemRepoBinding {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_repo,
            parent,
            false
        )
        binding.showFullName = showFullName
        binding.root.setOnClickListener {
            binding.repo?.let {
                repoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemRepoBinding, item: Repo) {
        binding.repo = item
    }
}