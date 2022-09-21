package com.example.myviewmodel.retrofit

import com.example.myviewmodel.models.MyToDo
import com.example.myviewmodel.models.MyToDoPostRequest
import com.example.myviewmodel.models.MyTodoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ViewModelService {

    @GET("plan")
    fun getAllToDo():Call<List<MyToDo>>

    @POST("plan/")
    fun addToDo(@Body myToDoPostRequest: MyToDoPostRequest):Call<MyTodoResponse>
}