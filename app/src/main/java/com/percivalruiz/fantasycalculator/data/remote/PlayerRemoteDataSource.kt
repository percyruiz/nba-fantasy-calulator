/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.percivalruiz.fantasycalculator.data.remote

import com.percivalruiz.fantasycalculator.data.PlayerDataSource
import com.percivalruiz.fantasycalculator.data.model.Player
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


/**
 * Implementation of the data source that adds a latency simulating network.
 */
class PlayerRemoteDataSource : PlayerDataSource {
  override fun getPlayers(callback: PlayerDataSource.LoadPlayersCallback) {

  }

  override fun getPlayer(callback: PlayerDataSource.GetPlayerCallback) {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://data.nba.net/10s/prod/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
    var call: Call<Player> = apiService.getPlayer("2018", "201939")
    call.enqueue(object : Callback<Player> {
      override fun onResponse(call: Call<Player>, response: Response<Player>) {
        Timber.d(response.body().toString())
      }

      override fun onFailure(call: Call<Player>, t: Throwable) {
      }
    })
  }


}
