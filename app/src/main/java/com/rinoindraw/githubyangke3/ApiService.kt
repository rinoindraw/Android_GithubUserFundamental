package com.rinoindraw.githubyangke3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
        @Headers("Authorization: token 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")
    fun getUserList(
        @Query("q") id: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUsersDetail(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUsersFollowers(
        @Path("username") username: String
    ): Call<List<GithubUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUsersFollowing(
        @Path("username") username: String
    ): Call<List<GithubUser>>

}