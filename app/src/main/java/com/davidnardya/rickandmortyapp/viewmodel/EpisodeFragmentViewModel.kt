package com.davidnardya.rickandmortyapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.rickandmortyapp.dao.CharacterDao
import com.davidnardya.rickandmortyapp.db.CharactersDataBase
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
//    private val repository: CharactersRepository = CharactersRepository(characterDao)

    init {
//        val characterDao = CharactersDataBase.getDataBase(context).characterDao()
        charactersList.value = charactersRepository.readAllData.value
    }

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