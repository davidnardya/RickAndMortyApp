package com.davidnardya.rickandmortyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val charactersRepository: CharactersRepository) : ViewModel() {

    //Properties
    val characters = charactersRepository.getResults().cachedIn(viewModelScope)

    //Public methods
    suspend fun saveCharacterToDB(character: CharacterResult) {
        viewModelScope.launch {
            charactersRepository.addCharacter(character)
        }
    }
}