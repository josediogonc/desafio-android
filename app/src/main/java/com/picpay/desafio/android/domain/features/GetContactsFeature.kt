package com.picpay.desafio.android.domain.features

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.domain.repository.RemoteRepository
import com.picpay.desafio.android.domain.repository.LocalRepository
import com.picpay.desafio.android.data.repository.remote.model.error.RequestError
import com.picpay.desafio.android.data.repository.remote.model.resource.Resource
import com.picpay.desafio.android.domain.model.user.User

class GetContactsFeature(private val remoteRepository: RemoteRepository,
                         private val localRepository: LocalRepository) {

    suspend fun invoke(errorDialog : MutableLiveData<RequestError>) : List<User> {

        val usersResponse = remoteRepository.getAllContacts()
        when (usersResponse.status) {
            Resource.Status.SUCCESS -> {
                usersResponse.data?.let {
                    localRepository.saveAll(it)
                    return it
                }
            }
            else -> usersResponse.error?.let {
                errorDialog.postValue(it)
                localRepository.getAll().let { list ->
                    return list
                }
            }
        }
        return emptyList()
    }

}
