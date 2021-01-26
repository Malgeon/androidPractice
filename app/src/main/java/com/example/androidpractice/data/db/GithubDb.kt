package com.example.androidpractice.data.db

import androidx.room.RoomDatabase

abstract class GithubDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}