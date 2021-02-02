package com.example.bliss

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.bliss", appContext.packageName)
    }

    @Test
    fun launchAppCorrectly() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun isLoginFragmentInView() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun isEmojiImageInView() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.emoji_image)).check(matches(isDisplayed()))
    }

    @Test
    fun isEmojiRandomButtonInViewAndClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.emoji_random)).check(matches(isDisplayed()))
        onView(withId(R.id.emoji_random)).perform(click())
    }

    @Test
    fun isEmojiListButtonInViewAndClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.emoji_list)).check(matches(isDisplayed()))
        onView(withId(R.id.emoji_list)).perform(click())
    }

    @Test
    fun isSearchAvatarButtonInViewAndClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.search_avatar)).check(matches(isDisplayed()))
        onView(withId(R.id.search_avatar)).perform(click())
    }

    @Test
    fun isAvatarListButtonInViewAndClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.avatar_list)).check(matches(isDisplayed()))
        onView(withId(R.id.avatar_list)).perform(click())
    }

    @Test
    fun isGoogleReposButtonInViewAndClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.google_repos)).check(matches(isDisplayed()))
        onView(withId(R.id.google_repos)).perform(click())
    }

    @Test
    fun isUserNameSearchClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.avatar_name)).check(matches(isDisplayed()))

        onView(withId(R.id.avatar_name)).perform(typeText("blissapps")
                ,ViewActions.closeSoftKeyboard())
        onView(withId(R.id.search_avatar)).perform(click())
    }

}