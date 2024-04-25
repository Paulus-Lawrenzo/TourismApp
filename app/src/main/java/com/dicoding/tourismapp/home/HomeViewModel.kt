package com.dicoding.tourismapp.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {
    private val _tourism = MutableLiveData<Resource<List<Tourism>>>()
    val tourism: LiveData<Resource<List<Tourism>>>
        get() = _tourism

    init {
        fetchTourismData()
    }

    private fun fetchTourismData() {
        viewModelScope.launch {
            tourismUseCase.getAllTourism()
                .onStart { _tourism.value = Resource.Loading() }
                .catch { exception ->
                    Log.e("ya", "catch")
                    _tourism.value = Resource.Error(exception.message ?: "Unknown error")
                }
                .collect { result ->
                    Log.e("ya", "collect")
                    _tourism.value = result
                }
        }
    }

    fun retry() {
        fetchTourismData()
    }


//    val tourism = tourismUseCase.getAllTourism().asLiveData()


//    private val _tourism = MutableLiveData<Resource<List<Tourism>>>()
//    val tourism: LiveData<Resource<List<Tourism>>>
//        get() = _tourism
//
//    init {
//        getAllTourism()
//    }
//
//    private fun getAllTourism() {
//        viewModelScope.launch {
//            _tourism.value = Resource.Loading()
//            try {
//                val result = tourismUseCase.getAllTourism()
//                _tourism.value = Resource.Success(result)
//            } catch (e : Exception) {
//                _tourism.value = Resource.Error(e.message ?: "Unknown error")
//            }
//        }
//    }
//
//    fun retryGetAllTourism() {
//        getAllTourism()
//    }
}

