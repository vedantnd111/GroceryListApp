package com.example.grocerylistapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocerylistapp.data.repository.ShoppingListRepository

@SuppressWarnings("Unchecked")
class ShoppingListViewModelFactory(private val repository: ShoppingListRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(repository) as T
    }
}