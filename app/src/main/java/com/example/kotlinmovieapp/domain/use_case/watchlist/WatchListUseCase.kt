package com.example.kotlinmovieapp.domain.use_case.watchlist

import android.util.Log
import com.example.kotlinmovieapp.data.local.entities.WatchListMedia
import com.example.kotlinmovieapp.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WatchListUseCase @Inject constructor(
    private val watchList: WatchListRepository
){

    fun getAll() : Flow<List<WatchListMedia>> = flow {
        try {
            val watchList = watchList.getAllLists()
            emit(watchList)
        } catch (e: Exception) {
            Log.e("WATCHLIST", e.toString())
        }

    }


    fun getList(list: String) : Flow<List<WatchListMedia>> = flow {
        try {
            val watchList = watchList.getList(list)
            emit(watchList)
        } catch (e: Exception) {
            Log.e("WATCHLIST", e.toString())
        }

    }

    suspend fun addToWatchList(media: WatchListMedia) {
        try {
            watchList.addToWatchList(media)
        } catch (e: Exception) {
            Log.e("WATCHLIST", e.toString())
        }

    }

    fun getMediaById(id: String) : Flow<WatchListMedia> = flow {
        try {
           val res = watchList.getMediaById(id)
            Log.e("RES", res.toString())
            emit(res)
        } catch (e: Exception) {
            Log.e("WATCHLIST", e.toString())
        }
    }

    suspend fun deleteFromList(media: WatchListMedia) {
        try {
            watchList.deleteFromList(media)
        } catch (e: Exception) {
            Log.e("WATCHLIST DELETE", e.toString())
        }
    }

}