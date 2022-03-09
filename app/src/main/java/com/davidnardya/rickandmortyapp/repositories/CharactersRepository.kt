package com.davidnardya.rickandmortyapp.repositories

import androidx.lifecycle.LiveData
import com.davidnardya.rickandmortyapp.api.SimpleApi
import com.davidnardya.rickandmortyapp.dao.CharacterDao
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val apiService: SimpleApi
) {

    val readAllData: LiveData<List<CharacterResult>> = characterDao.readAllData()

    suspend fun addCharacter(character: CharacterResult) {
        characterDao.addCharacter(character)
    }

    suspend fun getCharacters(): List<CharacterResult> {
        return apiService.getCharacters().characterResults
    }

    suspend fun getCharactersPerEpisode(episode: Episode): List<CharacterResult> {
        val charactersList = mutableListOf<CharacterResult>()

        episode.characters.forEach { character ->
            if (character.contains("https://rickandmortyapi.com/api/character/")) {
                val characterId =
                    character.replace("https://rickandmortyapi.com/api/character/", "")
                val characterResult = apiService.getSingleCharacter(characterId.toInt())
                charactersList.add(characterResult)
            }
        }

        return charactersList
    }

}