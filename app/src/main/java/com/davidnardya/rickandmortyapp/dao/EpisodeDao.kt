package com.davidnardya.rickandmortyapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidnardya.rickandmortyapp.models.Episode

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisode(episode: Episode)

    @Query("SELECT * FROM episodes_table")
    fun readAllData(): LiveData<List<Episode>>
}