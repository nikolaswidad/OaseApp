package com.nikolaswidad.oaseapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikolaswidad.oaseapp.data.NewsRepository
import com.nikolaswidad.oaseapp.di.Injection
import com.nikolaswidad.oaseapp.ui.screen.bookmark.BookmarkViewModel
import com.nikolaswidad.oaseapp.ui.screen.home.HomeViewModel

// Bisa
//class ViewModelFactory(private val repository: NewsRepository) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(repository) as T
////        } else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
////            return DetailRewardViewModel(repository) as T
//        } else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
//            return BookmarkViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//}
class ViewModelFactory private constructor(private val repository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
//        } else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
//            return DetailRewardViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}