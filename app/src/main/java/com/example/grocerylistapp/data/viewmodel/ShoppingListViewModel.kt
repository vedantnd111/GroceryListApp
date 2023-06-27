package com.example.grocerylistapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.data.repository.ShoppingListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val repository: ShoppingListRepository): ViewModel() {

    fun insertOrReplace(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        Log.d("####", "insertOrReplace")
        repository.insertOrReplace(item)
    }

    fun remove(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.remove(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}
