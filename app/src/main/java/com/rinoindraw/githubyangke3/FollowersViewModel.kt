package com.rinoindraw.githubyangke3

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.Dispatchers

class FollowersViewModel: ViewModel() {

    private val _listFollowers = MutableLiveData<List<GithubUser>>()
    val listFollowers: LiveData<List<GithubUser>> = _listFollowers

    fun setListFollowers(username: String) {
        ApiConfig.getApiService()
                .getUsersFollowers(username)
                .enqueue(object : Callback<List<GithubUser>> {
            override fun onResponse(call: Call<List<GithubUser>>, response: Response<List<GithubUser>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listFollowers.value = response.body()
                    }
                    else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                Log.e("FollowersViewModel", "Failed to get followers: ${t.message}")
            }
        })
    }
}

