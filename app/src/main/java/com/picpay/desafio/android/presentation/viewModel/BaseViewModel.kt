package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.repository.remote.model.error.RequestError

abstract class BaseViewModel() : ViewModel() {

    val loading = MutableLiveData(true)
    val errorDialog = MutableLiveData<RequestError>()

    fun loading(value : Boolean) = loading.postValue(value)

}
