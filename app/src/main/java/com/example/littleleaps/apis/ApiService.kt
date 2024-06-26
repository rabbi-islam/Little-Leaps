package com.example.littleleaps.apis

import com.example.littleleaps.model.TextResponseForReadingAlphabetApi
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("transcribe")
    fun sendAudio(@Part file: MultipartBody.Part): Call<TextResponseForReadingAlphabetApi>
}