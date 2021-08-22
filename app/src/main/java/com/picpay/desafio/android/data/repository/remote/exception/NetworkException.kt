package com.picpay.desafio.android.data.repository.remote.exception

class NetworkException(message: String = ErrorMessages.NETWORK_ERROR_MESSAGE) : Exception(message)

object ErrorMessages{
    const val NETWORK_ERROR_MESSAGE = "Não foi possível conectar com o servidor. Verifique sua conexão com a internet"
}