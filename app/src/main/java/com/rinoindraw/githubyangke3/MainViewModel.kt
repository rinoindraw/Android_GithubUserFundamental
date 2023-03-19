package com.rinoindraw.githubyangke3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<ItemsItem>>()

    fun setSearchUsers(query: String){
        ApiConfig.getApiService()
            .getUserList(query)
            .enqueue(object : Callback<GithubResponse>{
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items as ArrayList<ItemsItem>?)
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getSetSearchUsers(): LiveData<ArrayList<ItemsItem>> {
        return listUsers
    }

}