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
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUsersDetail(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUsersFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUsersFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

}