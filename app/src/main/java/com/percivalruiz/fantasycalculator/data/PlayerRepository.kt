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
package com.percivalruiz.fantasycalculator.data

import com.percivalruiz.fantasycalculator.data.model.Player
import java.util.*

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 *
 *
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
class PlayerRepository(
    private val playerRemoteDataSource: PlayerDataSource
) : PlayerDataSource {

  override fun getPlayers(callback: PlayerDataSource.LoadPlayersCallback) {

  }


  override fun getPlayer(callback: PlayerDataSource.GetPlayerCallback) {
    playerRemoteDataSource.getPlayer(object : PlayerDataSource.GetPlayerCallback {
      override fun onPlayerLoaded(player: Player) {
        callback.onPlayerLoaded(player)
      }

      override fun onDataNotAvailable() {
        callback.onDataNotAvailable()
      }
    })
  }


  companion object {

    private var INSTANCE: PlayerRepository? = null

    /**
     * Returns the single instance of this class, creating it if necessary.

     * @param playerRemoteDataSource the backend data source
     * *
     * @param playerLocalDataSource  the device storage data source
     * *
     * @return the [PlayerRepository] instance
     */
    @JvmStatic
    fun getInstance(playerRemoteDataSource: PlayerDataSource) =
        INSTANCE ?: synchronized(PlayerRepository::class.java) {
          INSTANCE ?: PlayerRepository(playerRemoteDataSource)
              .also { INSTANCE = it }
        }


    /**
     * Used to force [getInstance] to create a new instance
     * next time it's called.
     */
    @JvmStatic
    fun destroyInstance() {
      INSTANCE = null
    }
  }
}
