package com.davidnardya.rickandmortyapp.models


import com.google.gson.annotations.SerializedName

data class CharactersResult(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characterResults: List<CharacterResult>
)