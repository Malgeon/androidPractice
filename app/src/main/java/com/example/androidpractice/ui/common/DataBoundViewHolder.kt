package com.example.androidpractice.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 공변성으로 ViewDataBinding 하위자료형이 오게끔 함.
 */
class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) :
        RecyclerView.ViewHolder(binding.root)