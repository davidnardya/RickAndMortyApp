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

    companion object{
        @Volatile
        private var INSTANCE: CharactersDataBase? = null

        fun getDataBase(context: Context): CharactersDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDataBase::class.java,
                    "characters_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}