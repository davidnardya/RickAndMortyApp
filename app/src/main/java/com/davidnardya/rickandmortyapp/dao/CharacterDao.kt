package com.davidnardya.rickandmortyapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidnardya.rickandmortyapp.models.CharacterResult

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: CharacterResult)

    @Query("SELECT * FROM characters_table")
    fun readAllData(): LiveData<List<CharacterResult>>
}