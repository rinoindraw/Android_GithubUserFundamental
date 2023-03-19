package com.rinoindraw.githubyangke3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.Dispatchers

class FollowersViewModel: ViewModel() {

    private val _listFollowers = MutableLiveData<List<ItemsItem>>()
    val listFollowers: LiveData<List<ItemsItem>> = _listFollowers

    fun setListFollowers(username: String) {
        ApiConfig.getApiService()
                .getUsersFollowers(username)
                .enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>) {
                if (response.isSuccessful) {
                    _listFollowers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                Log.e("FollowersViewModel", "Failed to get followers: ${t.message}")
            }
        })
    }
}

