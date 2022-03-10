package com.davidnardya.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import com.davidnardya.rickandmortyapp.repositories.EpisodesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterFragmentViewModel @Inject constructor(private val episodesRepository: EpisodesRepository) : ViewModel() {

    //Properties
    var episodes: MutableLiveData<List<Episode>> = MutableLiveData()

    //Public methods
    fun getEpisodes(characterResult: CharacterResult) {
        viewModelScope.launch {
            episodes.value = episodesRepository.getEpisodes(characterResult)
            if (episodes.value != null) {
                episodes.value!!.forEach { episode ->
                    episodesRepository.addEpisode(episode)
                }
            }
        }
    }
}