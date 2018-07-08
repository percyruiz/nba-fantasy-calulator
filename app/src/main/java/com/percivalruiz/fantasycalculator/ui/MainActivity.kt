package com.percivalruiz.fantasycalculator.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.percivalruiz.fantasycalculator.R
import com.percivalruiz.fantasycalculator.data.PlayerDataSource
import com.percivalruiz.fantasycalculator.data.PlayerRepository
import com.percivalruiz.fantasycalculator.data.model.Player
import com.percivalruiz.fantasycalculator.data.remote.PlayerRemoteDataSource

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    var playerRepository = PlayerRepository.getInstance(PlayerRemoteDataSource())

    playerRepository.getPlayer(object : PlayerDataSource.GetPlayerCallback{
      override fun onPlayerLoaded(player: Player) {

      }

      override fun onDataNotAvailable() {

      }

    })
  }
}
