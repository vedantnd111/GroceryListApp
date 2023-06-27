package com.example.grocerylistapp.ui

import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.db.ShoppingDatabase
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.data.repository.ShoppingListRepository
import com.example.grocerylistapp.data.viewmodel.ShoppingListViewModel
import com.example.grocerylistapp.data.viewmodel.ShoppingListViewModelFactory
import com.example.grocerylistapp.ui.dialog.AddShoppingItemDialog
import com.example.grocerylistapp.ui.utilities.ShoppingItemsAdapter
import kotlinx.android.synthetic.main.activity_shopping.fab
import kotlinx.android.synthetic.main.activity_shopping.shoppingItems

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val db = ShoppingDatabase(this)
        val shoppingListRepository = ShoppingListRepository(db)
        val shoppingListViewModelFactory =
            ShoppingListViewModelFactory(repository = shoppingListRepository)
        val viewModel = ViewModelProviders.of(this, shoppingListViewModelFactory)
            .get(ShoppingListViewModel::class.java)

        val adapter = ShoppingItemsAdapter(listOf(), viewModel)
        shoppingItems.layoutManager = LinearLayoutManager(this)
        shoppingItems.adapter = adapter
        shoppingItems.addItemDecoration(
            DividerItemDecoration(
                shoppingItems.context,
                shoppingItems.layoutDirection
            )
        )
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            Log.d("####", "fab clicked")
            AddShoppingItemDialog(this) { shoppingItem: ShoppingItem ->
                viewModel.insertOrReplace(
                    shoppingItem
                )
            }.show()
        }
    }
}
