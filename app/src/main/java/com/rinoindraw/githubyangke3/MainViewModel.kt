package com.rinoindraw.githubyangke3

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainViewModel : ViewModel() {

    private val _listGithubUser = MutableLiveData<List<GithubUser>>()
    val listGithubUser: LiveData<List<GithubUser>> = _listGithubUser

    fun setSearchUsers(query: String) {
        ApiConfig.getApiService()
            .getUserList(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _listGithubUser.value = response.body()?.githubUsers

                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
    }
}

