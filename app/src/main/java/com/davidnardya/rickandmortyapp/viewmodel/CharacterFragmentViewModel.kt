package com.davidnardya.rickandmortyapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.db.EpisodeDataBase
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import com.davidnardya.rickandmortyapp.repositories.EpisodesRepository
import kotlinx.coroutines.launch

class CharacterFragmentViewModel(context: Context) : ViewModel() {

    //Properties
    var episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    private val repository: EpisodesRepository

    init {
        val episodeDao = EpisodeDataBase.getDataBase(context).episodeDao()
        repository = EpisodesRepository(episodeDao)
    }

    fun getEpisodes(characterResult: CharacterResult) {
        viewModelScope.launch {
            episodes.value = repository.getEpisodes(characterResult)
            if (episodes.value != null) {
                episodes.value!!.forEach { episode ->
                    repository.addEpisode(episode)
                }
            }
        }
    }
}