package com.example.grocerylistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.db.ShoppingDatabase
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.data.repository.ShoppingListRepository
import com.example.grocerylistapp.data.viewmodel.ShoppingListViewModel
import com.example.grocerylistapp.data.viewmodel.ShoppingListViewModelFactory
import com.example.grocerylistapp.databinding.ActivityShoppingBinding
import com.example.grocerylistapp.ui.dialog.AddShoppingItemDialog
import com.example.grocerylistapp.ui.utilities.ShoppingItemsAdapter

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val db = ShoppingDatabase(this)
        val shoppingListRepository = ShoppingListRepository(db)
        val shoppingListViewModelFactory = ShoppingListViewModelFactory(repository = shoppingListRepository)
        val viewModel = ViewModelProviders.of(this, shoppingListViewModelFactory).get(ShoppingListViewModel::class.java)

        val binding = ActivityShoppingBinding.inflate(LayoutInflater.from(this))
        val adapter = ShoppingItemsAdapter(listOf(), viewModel)
        binding.shoppingItems.layoutManager = LinearLayoutManager(this)
        binding.shoppingItems.adapter = adapter
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this) {shoppingItem:ShoppingItem -> viewModel.insertOrReplace(shoppingItem)}
        }
    }
}
