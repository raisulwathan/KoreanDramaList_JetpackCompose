package com.example.submission.data

import androidx.compose.runtime.mutableStateListOf
import com.example.submission.model.FakeSerialDataSource
import com.example.submission.model.Serial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SerialRepository {

    private val series = mutableListOf<Serial>()
    private val favoriteSeries = mutableStateListOf<Serial>()

    init {
        if (series.isEmpty()) {
            FakeSerialDataSource.dummySeries.forEach {
                series.add(it)
            }
        }
    }

    fun getAllSeries(query: String): Flow<List<Serial>> {
        return flow {
            val filteredSeries = series.filter { it.title.contains(query, ignoreCase = true) }
            emit(filteredSeries)
        }
    }

    fun getSerialById(seriesId: Long): Serial {
        return series.first {
            it.id == seriesId
        }
    }

    fun getAddedSeriesFavorite(): Flow<List<Serial>> {
        return flow {
            emit(series.filter { it.isFav })
        }
    }

    fun addToFavorite(series: Serial) {
        series.isFav = true
    }

    fun updateSeriesFavorite(seriesId: Long) {
        val animeToUpdate = series.find { it.id == seriesId }
        animeToUpdate?.let {
            it.isFav = !it.isFav
            if (it.isFav) {
                favoriteSeries.add(it)
            } else {
                favoriteSeries.remove(it)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: SerialRepository? = null

        fun getInstance(): SerialRepository =
            instance ?: synchronized(this) {
                SerialRepository().apply {
                    instance = this
                }
            }
    }
}