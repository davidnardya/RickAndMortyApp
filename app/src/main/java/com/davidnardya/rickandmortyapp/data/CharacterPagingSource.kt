package com.davidnardya.rickandmortyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davidnardya.rickandmortyapp.api.SimpleApi
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.repositories.CharactersRepository
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val simpleApi: SimpleApi,
    private val charactersRepository: CharactersRepository
) : PagingSource<Int, CharacterResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        val position = params.key ?: CHARACTER_STARTING_PAGE_INDEX

        return try {
            val response = simpleApi.getCharactersFromPage(position)
            val characters = response.characterResults
            characters.forEach {
                charactersRepository.addCharacter(it)
            }

            LoadResult.Page(
                data = characters,
                prevKey = if(position == CHARACTER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(characters.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return null
    }
}