package com.davidnardya.rickandmortyapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidnardya.rickandmortyapp.dao.CharacterDao
import com.davidnardya.rickandmortyapp.models.Converters
import com.davidnardya.rickandmortyapp.models.CharacterResult

@Database(entities = [CharacterResult::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharactersDataBase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: CharactersDataBase? = null

        fun getDatabase(context: Context): CharactersDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, CharactersDataBase::class.java, "characters_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}