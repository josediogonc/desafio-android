package com.picpay.desafio.android

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.presentation.activity.ContactsListActivity
import org.junit.Before
import org.junit.Test

class ContactsListActivityTest {

    private lateinit var context: Context
    private lateinit var title: String

    @Before
    fun before() {
        //server = MockWebServer()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        title = context.resources.getString(R.string.title)
    }

    @Test
    fun checkIfTitleIsVisible() {
        launchActivity<ContactsListActivity>().apply {
            onView(withText(title)).check(matches(isCompletelyDisplayed()))
        }
    }

    @Test
    fun checkIfContactsListIsDisplayed() {
        launchActivity<ContactsListActivity>().apply {
            onView(withId(R.id.recyclerView)).let {
                it.check(matches(isEnabled()))
                sleep()
                it.check(matches(isDisplayed()))
            }
        }
    }

    @Test
    fun checkIfContactsListIsNotEmpty() {

        launchActivity<ContactsListActivity>().apply {

            onView(withId(R.id.recyclerView))
                .check(matches(isEnabled()))

            sleep()

            onView(withId(R.id.recyclerView))
                .check(matches(RecyclerViewMatchers.isNotEmpty()))

        }
    }


    companion object {
        const val SLEEP_TIME_IN_MILI = 3000L
        fun sleep() = Thread.sleep(SLEEP_TIME_IN_MILI)
    }
}