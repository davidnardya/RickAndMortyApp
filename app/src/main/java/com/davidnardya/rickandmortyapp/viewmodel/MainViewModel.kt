package com.davidnardya.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val charactersRepository: CharactersRepository) : ViewModel() {

    //Properties
    var charactersList: MutableLiveData<List<CharacterResult>> = MutableLiveData()
    val characters = charactersRepository.getResults().cachedIn(viewModelScope)

    init {
        charactersList.value = charactersRepository.readAllData.value
    }

    fun getCharacters() {
        viewModelScope.launch {
            charactersList.value = charactersRepository.getCharacters()
            if (charactersList.value != null) {
                charactersList.value!!.forEach { character ->
                    charactersRepository.addCharacter(character)
                }
            }
        }
    }
}