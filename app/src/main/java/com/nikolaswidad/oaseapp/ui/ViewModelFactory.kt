package com.nikolaswidad.oaseapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: ArticlesRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}