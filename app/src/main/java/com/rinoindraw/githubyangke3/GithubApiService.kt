package com.rinoindraw.githubyangke3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class GithubApiService {
    data class GithubUser(
        val login: String,
        val id: Int,
        val avatar_url: String
    )

    interface GitApiService {
        @GET("/users/{username}/followers")
        fun getFollowers(@Path("username") username: String): Call<List<GithubUser>>
    }
}