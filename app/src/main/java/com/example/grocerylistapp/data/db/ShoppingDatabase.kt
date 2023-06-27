package com.example.grocerylistapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grocerylistapp.data.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

    companion object {

        // we use @volatile to make writes to this instance available instantly to all the threads
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // this will be execute when we do ShoppingDatabase()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}
