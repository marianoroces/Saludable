package com.marianoroces.norris.tpfinal

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marianoroces.norris.tpfinal.fragment.LoginFragment
import com.marianoroces.norris.tpfinal.view.LoginActivity
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<LoginFragment>()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testLogin() {
        onView(withId(R.id.fm_first)).perform(typeText("user"))
        onView(withId(R.id.fl_password)).perform(typeText("pass"))
        onView(withId(R.id.fl_login)).perform(click())
    }
}