package com.example.lab09

import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuotes(): QuoteResponse

    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: Int): QuoteModel
}