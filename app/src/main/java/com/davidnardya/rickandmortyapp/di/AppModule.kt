package com.davidnardya.rickandmortyapp.di

import android.content.Context
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
    fun provideMainViewModel(@ApplicationContext context: Context) = MainViewModel(context)

    @Singleton
    @Provides
    fun provideCharacterFragmentViewModel(@ApplicationContext context: Context) =
        CharacterFragmentViewModel(context)

    @Singleton
    @Provides
    fun provideEpisodeFragmentViewModel(@ApplicationContext context: Context) =
        EpisodeFragmentViewModel(context)


}