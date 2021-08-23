package com.picpay.desafio.android.data.repository.remote.model.response

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.picpay.desafio.android.data.repository.remote.model.error.RequestError
import com.picpay.desafio.android.data.repository.remote.model.resource.Resource
import com.picpay.desafio.android.utils.ConnectionChecker
import com.squareup.moshi.JsonEncodingException
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class RetrofitResponse<T>(
    context: Context,
    private val request: suspend () -> Response<T>
) : ApiResponse<T> {

    private val connectionChecker = ConnectionChecker(context)

    override suspend fun result(): Resource<T> {
        return try {
            val response = request.invoke()
            val data = response.body()

            if (response.isSuccessful && data != null) {
                success(data)
            } else {
                error(response.code(), response.errorBody())
            }
        } catch (exception: Exception) {
            failure(exception)
        }
    }

    override fun success(data: T) = Resource.success(data)

    override fun error(code: Int, errorBody: ResponseBody?): Resource<T> {
        return Resource.error(genericError)
    }

    override fun failure(exception: Exception): Resource<T> {
        return when (exception) {
            is JsonEncodingException -> {
                Resource.error(genericError)
            }
            is UnknownHostException, is IOException -> {
                Resource.error(verifyInternetConnection())
            }
            else -> {
                Resource.error(genericError)
            }
        }
    }

    private fun verifyInternetConnection(): RequestError {
        return if (connectionChecker.hasInternetConnection()) {
            genericError
        } else {
            connectionError
        }
    }

    companion object {

        val connectionError: RequestError
            get() = RequestError(
                code = 401,
                title = "Erro de conexão",
                message = "Não foi possível conectar com o servidor. Verifique sua conexão com a internet."
            )

        val genericError : RequestError
            get() = RequestError(
                code = 400,
                title = "Erro de conexão",
                message = "Não foi possível conectar com o servidor. ",
            )

    }
}