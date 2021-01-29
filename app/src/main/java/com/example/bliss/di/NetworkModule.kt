package com.example.bliss.di

import com.example.bliss.deserialization.EmojiDeserializer
import com.example.bliss.model.EmojiResponse
import com.example.bliss.network.BlissInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val TIMEOUT = 30

    @Provides
    fun provideBaseUrl() = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT.toLong(), java.util.concurrent.TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT.toLong(), java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesGsonDeserializer() : Gson{
        return GsonBuilder()
            .registerTypeAdapter(EmojiResponse::class.java, EmojiDeserializer())
            .create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String, gson: Gson): Retrofit{

        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(BlissInterface::class.java)
}