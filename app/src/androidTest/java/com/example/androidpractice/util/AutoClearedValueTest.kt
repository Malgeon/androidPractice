package com.example.androidpractice.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.androidpractice.testing.SingleFragmentActivity
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.IllegalStateException

@RunWith(AndroidJUnit4::class)
class AutoClearedValueTest {

    @Rule // activity 환경 구축
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)
    private lateinit var testFragment: TestFragment


    @Before
    fun init() {
        testFragment = TestFragment()
        activityRule.activity.setFragment(testFragment)
        // Synchronously wait for the application to be idle. Fragment가 idle 상태 즉, 실행단계상태가 될때까지 기다리게 해주는 함수로 생각이 된다.
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun clearOnReplace() {
        activityRule.activity.replaceFragment(TestFragment())
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        try {
            testFragment.testValue // Null 처리를 했는데 접근이 가능하면 Fail
            Assert.fail("should throw if accessed when not set")
        } catch (ex: IllegalStateException) {
        }
    }

    @Test
    fun clearOnReplaceBackStack() {
        // autoCleared에 의해 testValue 값이 null임을 확인 후 다시 팝업 할때, 다시 set이 잘 되는지 확인
        activityRule.activity.replaceFragment(TestFragment(), true)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        try {
            testFragment.testValue
            Assert.fail("should throw if accessed when not set")
        } catch (ex: IllegalStateException) {
        }

        activityRule.activity.supportFragmentManager.popBackStack()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        MatcherAssert.assertThat(testFragment.testValue, Matchers.`is`("foo"))
    }

    @Test
    fun dontClearForChildFragment() {
        // fragment의 child를 관리 하고 있을때, 값이 유지되는지 확인
        testFragment.childFragmentManager.beginTransaction()
            .add(Fragment(), "foo").commit()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        MatcherAssert.assertThat(testFragment.testValue, Matchers.`is`("foo"))
    }

    @Test
    fun dontClearForDialog() {
        val dialogFragment = DialogFragment()
        dialogFragment.show(testFragment.parentFragmentManager, "dialog")
        dialogFragment.dismiss()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        MatcherAssert.assertThat(testFragment.testValue, Matchers.`is`("foo"))
    }


    class TestFragment : Fragment() {
        var testValue by autoCleared<String>()

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            testValue = "foo"
            return View(context)
        }
    }
}