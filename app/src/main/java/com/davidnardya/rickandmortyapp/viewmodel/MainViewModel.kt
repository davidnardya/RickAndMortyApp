package com.davidnardya.rickandmortyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    //Properties
    val characters = charactersRepository.getResults().cachedIn(viewModelScope)
}