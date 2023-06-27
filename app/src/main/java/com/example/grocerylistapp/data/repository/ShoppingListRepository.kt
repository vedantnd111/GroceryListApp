package com.example.grocerylistapp.data.repository

import com.example.grocerylistapp.data.db.ShoppingDatabase
import com.example.grocerylistapp.data.entities.ShoppingItem

class ShoppingListRepository(private val db: ShoppingDatabase) {

    suspend fun insertOrReplace(item:ShoppingItem) = db.shoppingDao().insertOrReplace(item)

    suspend fun remove(item: ShoppingItem) = db.shoppingDao().remove(item)

    fun getAllShoppingItems() = db.shoppingDao().getAllShoppingItems()
}
