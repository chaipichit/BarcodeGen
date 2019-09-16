package com.example.barcodegender.api

import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @POST("exec")
    fun sell(@Body productSell: ProductSell
        ): Call<SellCallBack>

}