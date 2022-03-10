package com.davidnardya.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeFragmentViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    //Properties
    var charactersList: MutableLiveData<List<CharacterResult>> = MutableLiveData()

    //Initialize block
    init {
        charactersList.value = charactersRepository.readAllData.value
    }

    //Public methods
    fun getCharacters(episode: Episode) {
        viewModelScope.launch {
            charactersList.value = charactersRepository.getCharactersPerEpisode(episode)
            if (charactersList.value != null) {
                charactersList.value!!.forEach { character ->
                    charactersRepository.addCharacter(character)
                }
            }
        }
    }
}