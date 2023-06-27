package com.example.grocerylistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grocerylistapp.data.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(item: ShoppingItem)

    @Delete
    suspend fun remove(item: ShoppingItem)

    @Query("SELECT * FROM `shopping_list`")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}
