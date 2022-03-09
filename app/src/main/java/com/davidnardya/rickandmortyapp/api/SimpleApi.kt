package com.davidnardya.rickandmortyapp.api

import com.davidnardya.rickandmortyapp.models.CharactersResult
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("/api/character")
    suspend fun getCharacters(
    ): CharactersResult

    @GET("/api/character")
    suspend fun getCharactersFromPage(
        @Query("page") page: Int
    ): CharactersResult

    @GET("/api/character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): CharacterResult

    @GET("/api/episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): Episode

}