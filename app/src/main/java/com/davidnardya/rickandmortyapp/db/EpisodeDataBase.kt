package com.davidnardya.rickandmortyapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidnardya.rickandmortyapp.dao.EpisodeDao
import com.davidnardya.rickandmortyapp.models.Converters
import com.davidnardya.rickandmortyapp.models.Episode


@Database(entities = [Episode::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EpisodeDataBase : RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao

    /*companion object {
        @Volatile
        private var INSTANCE: EpisodeDataBase? = null

        fun getDataBase(context: Context): EpisodeDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EpisodeDataBase::class.java,
                    "episodes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }*/

    companion object {
        @Volatile
        private var instance: EpisodeDataBase? = null

        fun getDatabase(context: Context): EpisodeDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, EpisodeDataBase::class.java, "episodes_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}