package com.example.icecream

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.icecream.repo.Repository

class Factory(private val repo : Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return com.example.icecream.ViewModel(repo) as T
    }
}