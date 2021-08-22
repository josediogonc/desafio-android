package com.picpay.desafio.android.repository

import android.content.Context
import com.picpay.desafio.android.data.repository.remote.datasource.RemoteRepositoryImpl
import com.picpay.desafio.android.data.repository.remote.service.PicPayService
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.domain.repository.RemoteRepository
import com.picpay.desafio.android.mock.UserMock
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteRepositoryTest {

    private lateinit var context: Context
    private lateinit var api: PicPayService
    private lateinit var repository: RemoteRepository

    @Before
    fun setUp() {
        context = mockk()
        api = mockk()
        repository = RemoteRepositoryImpl(context, api)
    }

    @Test
    fun getAllContacts_success_contactsListRetrieved() = runBlocking {
        val userList = UserMock.getContacsList()
        val response = Response.success(userList)

        // Given
        coEvery { api.getAllContacts() } returns response

        // When
        val list = repository.getAllContacts().data

        // Then
        assertEquals(list, userList)

    }

    @Test
    fun getAllContacts_error_contactsEmptyList() = runBlocking {

        val response = Response.error<List<User>>(400, "".toResponseBody(null))

        // Given
        coEvery { api.getAllContacts() } returns response

        // When
        val list = repository.getAllContacts().data
        val error = repository.getAllContacts().error

        // Then
        assertNull(list)
        assertEquals(error?.code, 400)

    }
}