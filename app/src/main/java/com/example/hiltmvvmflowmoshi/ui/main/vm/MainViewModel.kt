package com.example.hiltmvvmflowmoshi.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hiltmvvmflowmoshi.data.Dogs
import com.example.hiltmvvmflowmoshi.network.ApiServices
import com.example.hiltmvvmflowmoshi.repository.DogsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val apiServices:ApiServices) : ViewModel() {

    fun getAllDogs(): Flow<PagingData<Dogs>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false)
    ){
        DogsPagingSource(apiServices)
    }.flow.cachedIn(viewModelScope)
}