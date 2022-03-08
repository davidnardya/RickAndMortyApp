package com.davidnardya.rickandmortyapp.di

import android.content.Context
import com.davidnardya.rickandmortyapp.dao.CharacterDao
import com.davidnardya.rickandmortyapp.dao.EpisodeDao
import com.davidnardya.rickandmortyapp.db.CharactersDataBase
import com.davidnardya.rickandmortyapp.db.EpisodeDataBase
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import com.davidnardya.rickandmortyapp.repositories.EpisodesRepository
import com.davidnardya.rickandmortyapp.viewmodel.CharacterFragmentViewModel
import com.davidnardya.rickandmortyapp.viewmodel.EpisodeFragmentViewModel
import com.davidnardya.rickandmortyapp.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainViewModel(charactersRepository: CharactersRepository) = MainViewModel(charactersRepository)

    @Singleton
    @Provides
    fun provideCharacterFragmentViewModel(episodesRepository: EpisodesRepository) =
        CharacterFragmentViewModel(episodesRepository)

    @Singleton
    @Provides
    fun provideEpisodeFragmentViewModel(charactersRepository: CharactersRepository) =
        EpisodeFragmentViewModel(charactersRepository)

    @Singleton
    @Provides
    fun provideEpisodeDataBase(@ApplicationContext context: Context) = EpisodeDataBase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCharactersDataBase(@ApplicationContext context: Context) = CharactersDataBase.getDatabase(context)

    @Singleton
    @Provides
    fun provideEpisodeDao(dataBase: EpisodeDataBase) = dataBase.episodeDao()

    @Singleton
    @Provides
    fun provideCharacterDao(dataBase: CharactersDataBase) = dataBase.characterDao()

    @Singleton
    @Provides
    fun provideCharactersRepository(characterDao: CharacterDao) = CharactersRepository(characterDao)

    @Singleton
    @Provides
    fun provideEpisodeRepository(episodeDao: EpisodeDao) = EpisodesRepository(episodeDao)

}