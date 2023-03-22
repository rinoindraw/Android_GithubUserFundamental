package com.rinoindraw.githubyangke3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val _listDetail = MutableLiveData<DetailResponse>()
    val listDetail: LiveData<DetailResponse> = _listDetail
    fun setUserDetail(username: String) {
        ApiConfig.getApiService()
            .getUsersDetail(username)
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _listDetail.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }
}