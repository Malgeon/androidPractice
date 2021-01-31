package com.example.androidpractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidpractice.AppExecutors
import com.example.androidpractice.data.api.GithubService
import com.example.androidpractice.data.db.GithubDb
import com.example.androidpractice.data.db.RepoDao
import com.example.androidpractice.data.vo.Repo
import com.example.androidpractice.data.vo.Resource
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: GithubDb,
    private val repoDao: RepoDao,
    private val githubService: GithubService
) {
    fun search(query: String): LiveData<Resource<List<Repo>>> {
        var test = MutableLiveData<Resource<List<Repo>>>()
        return test
    }

    fun searchNextPage(query: String): LiveData<Resource<Boolean>> {
        var test = MutableLiveData<Resource<Boolean>>()
        return test
    }


}