package com.nikolaswidad.oaseapp.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import com.nikolaswidad.oaseapp.data.NewsRepository
import com.nikolaswidad.oaseapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookmarkViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<BookmarkState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<BookmarkState>>
        get() = _uiState
}