package com.example.taghive.data.di

import com.example.taghive.BuildConfig
import com.example.taghive.data.Constant
import com.example.taghive.data.remote.TagHiveApi
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This is an network module where singleton object of retrofit and interceptor is created
 */
val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY

        return@single loggingInterceptor
    }

    single {

        val builder = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()

                request = request.newBuilder()
                    .addHeader(
                        Constant.AUTH_KEY,
                        Constant.AUTH_KEY,
                    )
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(request)
            })
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .connectTimeout(0, TimeUnit.MILLISECONDS)
        return@single builder.build()
    }

    single {
        val objectMapper = jacksonObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return@single JacksonConverterFactory.create(objectMapper)
    }

    single {
        val builder = Retrofit.Builder()
            .baseUrl("https://api.wazirx.com/")
            .client(get<OkHttpClient>())
            .addConverterFactory(get<JacksonConverterFactory>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return@single builder.build()
    }

    single { return@single get<Retrofit>().create(TagHiveApi::class.java) }
}