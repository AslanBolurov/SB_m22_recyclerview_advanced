package com.skillbox.aslanbolurov.rickandmorty.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skillbox.aslanbolurov.rickandmorty.data.RickymortyRepository
import com.skillbox.aslanbolurov.rickandmorty.model.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    val repository: RickymortyRepository
) : ViewModel() {


    var throwable= MutableLiveData<Throwable?>(null)


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var pagedCharacters: Flow<PagingData<Results>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPagingSource(throwable) }
    ).flow.cachedIn(viewModelScope)


    fun reloadList() {
        pagedCharacters= Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPagingSource(throwable) }
        ).flow.cachedIn(viewModelScope)
    }
}





