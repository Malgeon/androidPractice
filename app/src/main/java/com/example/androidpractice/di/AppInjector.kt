package com.example.androidpractice.di

/**
 * DI를 자동적으로 해주기 위한 object
 * fragment에 injectable을 부여해서 inject fragment만 들어올 수 있도록장치를 마련해둠.
 * 그러나 Component.Factory가 나옴에 따라 DaggerFragment()로 사용하면 된다.
 */

/*
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.android.example.github.GithubApp
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {
    fun init(androidPracticeApp: AndroidPracticeApp) {
        DaggerAppComponent.factory().create(androidPracticeApp)
        /**
         * activity와 fragment를 자동으로 inject해주도록 하기 위한 작업
         * 이 작업을 안해두면 각각의 액티비티에서 AndroidInjection.inject(__)을 작성해줘야한다.
         */

        androidPracticeApp // registerActivityLifecycleCallbacks : current activity를 찾을 때 유용하다.
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })

    }


    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }

}

 */

