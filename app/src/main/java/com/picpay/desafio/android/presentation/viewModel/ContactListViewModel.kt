package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.features.GetContactsFeature
import com.picpay.desafio.android.domain.model.user.User
import kotlinx.coroutines.launch

class ContactListViewModel(private val getContactsFeature : GetContactsFeature) : BaseViewModel() {

    val contactList = MutableLiveData<List<User>>()

    fun loadContacts() {
        viewModelScope.launch {
            loading(true)
            fetchContacts()
            loading(false)
        }
    }

    private suspend fun fetchContacts() {
        val list : List<User> = getContactsFeature.invoke(errorDialog)
        contactList.postValue(list)
    }


}
