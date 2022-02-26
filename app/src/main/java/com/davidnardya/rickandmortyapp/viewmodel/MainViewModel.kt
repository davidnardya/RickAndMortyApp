package com.davidnardya.rickandmortyapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.db.CharactersDataBase
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    //Properties
    var charactersList: MutableLiveData<List<CharacterResult>> = MutableLiveData()
    private val repository: CharactersRepository

    init {
        val characterDao = CharactersDataBase.getDataBase(context).characterDao()
        repository = CharactersRepository(characterDao)
        charactersList.value = repository.readAllData.value
    }

    fun getCharacters() {
        viewModelScope.launch {
            charactersList.value = repository.getCharacters()
            if (charactersList.value != null) {
                charactersList.value!!.forEach { character ->
                    repository.addCharacter(character)
                }
            }
        }
    }
}