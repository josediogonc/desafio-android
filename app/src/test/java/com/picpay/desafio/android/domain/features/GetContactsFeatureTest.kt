package com.picpay.desafio.android.domain.features

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.picpay.desafio.android.data.repository.remote.model.error.RequestError
import com.picpay.desafio.android.data.repository.remote.model.resource.Resource
import com.picpay.desafio.android.data.repository.remote.model.response.RetrofitResponse
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.domain.repository.LocalRepository
import com.picpay.desafio.android.domain.repository.RemoteRepository
import com.picpay.desafio.android.mock.UserMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetContactsFeatureTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var SUT : GetContactsFeature
    private lateinit var mockedError: RequestError
    private lateinit var databaseMock: LocalRepository
    private lateinit var repositoryMock: RemoteRepository
    private lateinit var mockedContacts: List<User>
    private lateinit var errorDialog: MutableLiveData<RequestError>
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        errorDialog = MutableLiveData<RequestError>()
        mockedContacts = UserMock.getContacsList()
        repositoryMock = mockk()
        databaseMock = mockk()
        mockedError = RetrofitResponse.genericError

        SUT = GetContactsFeature(repositoryMock, databaseMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getContactList_success_getContactListFromRemoteRepository() = runBlockingTest {

        // Given
        coEvery { repositoryMock.getAllContacts() } returns Resource.success(mockedContacts)
        coEvery { databaseMock.saveAll(any()) } returns Unit

        // When
        val list = SUT.invoke(errorDialog)

        // Then
        assert(list.isNotEmpty())
        assertEquals(list, mockedContacts)

    }

    @Test
    fun getContactList_genericError_errorDialogCalled() = runBlockingTest {

        // Given
        coEvery { repositoryMock.getAllContacts() } returns Resource.error(mockedError)
        coEvery { databaseMock.getAll() } returns UserMock.getContacsList()

        // When
        val list = SUT.invoke(errorDialog)

        // Then
        assertEquals(errorDialog.value, mockedError)
        assertEquals(list, mockedContacts)

    }

    @Test
    fun getContactList_genericError_getContactListFromLocalRepository() = runBlockingTest {

        // Given
        coEvery { repositoryMock.getAllContacts() } returns Resource.error(mockedError)
        coEvery { databaseMock.getAll() } returns mockedContacts

        // When
        val list = SUT.invoke(errorDialog)

        // Then
        assert(list.isNotEmpty())
        assertEquals(list, mockedContacts)

    }


}