package com.nikolaswidad.oaseapp.ui.screen.home

//class HomeViewModel(
//    private val repository: ArticlesRepository
//) : ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<List<NewsBookmark>>> = MutableStateFlow(UiState.Loading)
//    val uiState: StateFlow<UiState<List<NewsBookmark>>>
//        get() = _uiState
//
//    fun getAllNews() {
//        viewModelScope.launch {
//            repository.getAllNews()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { newsBookmarks ->
//                    _uiState.value = UiState.Success(newsBookmarks)
//                }
//        }
//    }
//}