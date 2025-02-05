package com.picpay.desafio.android.data.repository.remote.model.resource

import com.picpay.desafio.android.data.repository.remote.model.error.RequestError

class Resource<T> private constructor(
    val status: Status,
    val data: T? = null,
    val error: RequestError? = null
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data = data
            )
        }

        fun <T> error(error: RequestError?): Resource<T> {
            return Resource(
                Status.ERROR,
                error = error
            )
        }
    }

    enum class Status {
        SUCCESS, ERROR
    }
}