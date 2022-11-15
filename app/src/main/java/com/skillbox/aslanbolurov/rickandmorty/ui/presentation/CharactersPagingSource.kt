package com.skillbox.aslanbolurov.rickandmorty.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skillbox.aslanbolurov.rickandmorty.data.RickymortyRepository
import com.skillbox.aslanbolurov.rickandmorty.model.Results

class CharactersPagingSource(
    var throwable: MutableLiveData<Throwable?>) : PagingSource<Int, Results>() {

    private val repository = RickymortyRepository()

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return FIRST_PAGE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getCharactersByPage(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                throwable.value=it
                LoadResult.Error(it)
            })
    }

    private companion object{
        private val FIRST_PAGE=1
    }

}