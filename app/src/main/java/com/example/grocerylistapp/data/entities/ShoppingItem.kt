package com.example.grocerylistapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list")
data class ShoppingItem(
    @ColumnInfo(name = "itemName") var firstName:String,
    @ColumnInfo(name = "itemAmount") var amount:Int) {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}
