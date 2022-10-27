package com.example.jakewhartongithubrepositories.core.data.remote


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NetworkServiceCall {
    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<T>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                val response = apiCall.invoke()

                NetworkResponse.Success(response)
            } catch (throwable: Exception) {
                Log.d("safeApiCall", "safeApiCall: ${throwable.localizedMessage}")
                NetworkResponse.Failure(throwable)
            }
        }
    }
}