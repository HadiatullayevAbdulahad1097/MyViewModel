package com.example.myviewmodel.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myviewmodel.models.MyToDo
import com.example.myviewmodel.models.MyToDoPostRequest
import com.example.myviewmodel.models.MyTodoResponse
import com.example.myviewmodel.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel(){

    private val liveData  = MutableLiveData<List<MyToDo>>()
    private val TAG = "MyViewModel"
    fun getAllToDo() : MutableLiveData<List<MyToDo>>{

        ApiClient.getApiService().getAllToDo().enqueue(object : Callback<List<MyToDo>>{
            override fun onResponse(call: Call<List<MyToDo>>, response: Response<List<MyToDo>>) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                    Log.d(TAG, "onResponse: ")
                }
            }

            override fun onFailure(call: Call<List<MyToDo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }
        })

        return liveData
    }

    fun addTodo(myToDoPostRequest: MyToDoPostRequest){
        ApiClient.getApiService().addToDo(myToDoPostRequest).enqueue(object : Callback<MyTodoResponse>{
            override fun onResponse(
                call: Call<MyTodoResponse>,
                response: Response<MyTodoResponse>
            ) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: ${response.body()}")
                    getAllToDo()
                }
            }

            override fun onFailure(call: Call<MyTodoResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }
        })
    }
}