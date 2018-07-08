package com.percivalruiz.fantasycalculator.data.remote

import com.percivalruiz.fantasycalculator.data.model.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("{year}/players/{player_id}_profile.json")
  fun getPlayer(@Path("year") year: String, @Path("player_id") playerId: String): Call<Player>

}