package com.davidnardya.rickandmortyapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.db.CharactersDataBase
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val charactersRepository: CharactersRepository) : ViewModel() {

    //Properties
    var charactersList: MutableLiveData<List<CharacterResult>> = MutableLiveData()
//    private val repository: CharactersRepository

    init {
//        val characterDao = CharactersDataBase.getDataBase(context).characterDao()
//        repository = CharactersRepository(characterDao)
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