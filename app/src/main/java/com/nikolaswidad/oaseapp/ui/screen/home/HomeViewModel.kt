package com.nikolaswidad.oaseapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolaswidad.oaseapp.data.NewsRepository
import com.nikolaswidad.oaseapp.model.NewsBookmark
import com.nikolaswidad.oaseapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<NewsBookmark>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsBookmark>>>
        get() = _uiState

    fun getAllNews() {
        viewModelScope.launch {
            repository.getAllNews()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { newsBookmarks ->
                    _uiState.value = UiState.Success(newsBookmarks)
                }
        }
    }
}