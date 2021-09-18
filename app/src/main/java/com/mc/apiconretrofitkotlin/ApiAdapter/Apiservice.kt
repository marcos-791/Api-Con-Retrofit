package com.mc.apiconretrofitkotlin.ApiAdapter

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface Apiservice {

    @GET("posts")
    fun showList() : Call<List<Post>>

    @GET("posts")
    fun find(@Query("q") q : String) : Call<List<Post>>

}