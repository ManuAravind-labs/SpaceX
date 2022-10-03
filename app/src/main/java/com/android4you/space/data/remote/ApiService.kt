package com.android4you.space.data.remote

import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.model.history.HistoryModel
import com.android4you.space.domain.model.pads.LandPadsModel
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.model.rockets.RocketModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("launches")
    suspend fun getAllLaunches(): Response<List<LaunchModel>>

    @GET("launches/{id}")
    suspend fun getOneLaunch(@Path("id") id: Int): Response<LaunchModel>

    @GET("launches/past")
    suspend fun getPastLaunches(): Response<List<LaunchModel>>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches(): Response<List<LaunchModel>>

    @GET("launches")
    suspend fun getLatestLaunch(): Response<LaunchModel>

    @GET("launches")
    suspend fun getNextLaunch(): Response<LaunchModel>

    @GET("rockets")
    suspend fun getAllRockets(): Response<List<RocketModel>>

    @GET("rockets/{id}")
    suspend fun getOneRocket(@Path("id") id: String): Response<RocketModel>

    @GET("crew")
    suspend fun getAllcrews(): Response<List<CrewModel>>

    @GET("payloads")
    suspend fun getAllpayloads(): Response<List<PayloadsModel>>

    @GET("history")
    suspend fun getAllHistory(): Response<List<HistoryModel>>

    @GET("landpads")
    suspend fun getAllLandPads(): Response<List<LandPadsModel>>

    @GET("launchpads")
    suspend fun getAllLaunchPads(): Response<List<LaunchPadsModel>>

    @GET("launchpads/{id}")
    suspend fun getOneLaunchPad(@Path("id") id: String): Response<LaunchPadsModel>
}
