package com.example.taghive.data.remote

import com.example.taghive.domain.model.ApiErrors
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackSingleWrapper<R> : DisposableObserver<R>() {
    protected abstract fun onApiSuccess(result: R)
    protected abstract fun onApiFailure(error: String)

    override fun onComplete() {
    }

    override fun onNext(result: R) {
        onApiSuccess(result)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> {
                if (e.code() == 401) {
                    val responseBody = e.response()?.errorBody()
                    onApiFailure(getErrorMessage(responseBody))
                } else {
                    val responseBody = e.response()?.errorBody()
                    onApiFailure(getErrorMessage(responseBody))
                }


            }
            is SocketTimeoutException -> onApiFailure("Network Timeout!")
            is IOException -> onApiFailure("Failed to connect to server!")
            else -> onApiFailure(e.localizedMessage.toString())
        }
    }

    /*
    * This method is used to get the message field from 401 error body.
    * */
    fun getErrorMessage(response: ResponseBody?): String {

        val error = response?.string()
        var apiErrors: ApiErrors? = null
        try {
            apiErrors = Gson().fromJson(error, ApiErrors::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (apiErrors?.error.isNullOrEmpty().not()) {
            return apiErrors?.error ?: "Something went wrong"
        } else {
            if (apiErrors?.errors?.mobile.isNullOrEmpty().not()) {
                return if (apiErrors?.errors?.mobile?.get(0).isNullOrEmpty().not()) {
                    apiErrors?.errors?.mobile?.get(0).toString()
                } else {
                    "Something went wrong"
                }
            } else if (apiErrors?.errors?.other.isNullOrEmpty().not()) {
                return if (apiErrors?.errors?.other?.get(0).isNullOrEmpty().not()) {
                    apiErrors?.errors?.other?.get(0).toString()
                } else {
                    "Something went wrong"
                }

            } else if (apiErrors?.errors?.email.isNullOrEmpty().not()) {
                return if (apiErrors?.errors?.email?.get(0).isNullOrEmpty().not()) {
                    "User not found"
                } else {
                    "Something went wrong"
                }
            }
            return "Something went wrong"
        }
    }

    fun getGenericErrorMessage(): String {
        return "Something went wrong"
    }
}