package com.example.starwarsexplorer.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsexplorer.domain.model.CharacterModel
import com.example.starwarsexplorer.domain.repository.StarWarsRepository

class StarWarsPeopleSource(
    private val repository: StarWarsRepository
): PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        val nextPage = params.key ?: 1
        val characters = repository.getCharacterList(
            pageNumber = nextPage,
            pageSize = params.loadSize
        )
        return LoadResult.Page(
            data = characters,
            nextKey = nextPage + 1,
            prevKey = if (nextPage == 1) null else nextPage - 1
        )
    }
}