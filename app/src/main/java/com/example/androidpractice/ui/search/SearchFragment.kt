package com.example.androidpractice.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentSearchBinding
import com.example.androidpractice.util.autoCleared
import dagger.android.support.DaggerFragment


class SearchFragment : DaggerFragment() {

    var binding by autoCleared<FragmentSearchBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}