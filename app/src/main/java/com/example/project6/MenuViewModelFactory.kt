package com.example.project6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MenuViewModelFactory(private val dao: NoteDao)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}