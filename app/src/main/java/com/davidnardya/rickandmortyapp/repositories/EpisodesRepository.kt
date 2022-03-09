package com.davidnardya.rickandmortyapp.repositories

import com.davidnardya.rickandmortyapp.api.SimpleApi
import com.davidnardya.rickandmortyapp.dao.EpisodeDao
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode

class EpisodesRepository(
    private val episodeDao: EpisodeDao,
    private val apiService: SimpleApi
) {

    suspend fun addEpisode(episode: Episode) {
        episodeDao.addEpisode(episode)
    }

    suspend fun getEpisodes(characterResult: CharacterResult): List<Episode> {
        val episodesStringList = apiService.getSingleCharacter(characterResult.id).episode
        val episodesList = mutableListOf<Episode>()

        episodesStringList.forEach { episode ->
            if (episode.contains("https://rickandmortyapi.com/api/episode/")) {
                val episodeId = episode.replace("https://rickandmortyapi.com/api/episode/", "")
                val episodeResult = apiService.getEpisode(episodeId.toInt())
                episodesList.add(episodeResult)
            }
        }

        return episodesList
    }

}