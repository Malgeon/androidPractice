package com.example.androidpractice.repository

import com.example.androidpractice.AppExecutors
import com.example.androidpractice.data.api.GithubService
import com.example.androidpractice.data.db.GithubDb
import com.example.androidpractice.data.db.RepoDao
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: GithubDb,
    private val repoDao: RepoDao,
    private val githubService: GithubService
) {

}