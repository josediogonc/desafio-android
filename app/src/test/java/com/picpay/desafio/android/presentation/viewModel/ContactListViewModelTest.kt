package com.picpay.desafio.android.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.domain.features.GetContactsFeature
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.mock.UserMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ContactListViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var SUT: ContactListViewModel
    private lateinit var getContactsListFeature: GetContactsFeature
    private lateinit var mockedContacts: List<User>
    private lateinit var userListObserver: Observer<List<User>>

    @Before
    fun setUp() {
        mockedContacts = UserMock.getContacsList()
        userListObserver = mock()
        getContactsListFeature = mockk()
        SUT = ContactListViewModel(getContactsListFeature).apply {
            contactList.observeForever(userListObserver)
        }
    }

    @Test
    fun loadContacts_success_contactListUpdated() {
        testCoroutineRule.runBlockingTest {

            // Given
            coEvery { getContactsListFeature.invoke(any()) } returns mockedContacts

            // When
            SUT.loadContacts()

            // Then
            verify(userListObserver).onChanged(SUT.contactList.value)
        }
    }

}