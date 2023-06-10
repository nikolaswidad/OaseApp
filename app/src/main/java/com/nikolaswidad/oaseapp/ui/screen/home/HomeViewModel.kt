package com.nikolaswidad.oaseapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolaswidad.oaseapp.data.NewsRepository
import com.nikolaswidad.oaseapp.data.entity.NewsEntity
import com.nikolaswidad.oaseapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<NewsEntity>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsEntity>>>
        get() = _uiState

    fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getAllNews()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ news ->
                    _uiState.value = UiState.Success(news)
                }
        }
    }
}