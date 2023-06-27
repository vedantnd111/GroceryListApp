package com.example.grocerylistapp.ui.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.data.viewmodel.ShoppingListViewModel
import com.example.grocerylistapp.databinding.ShoppingItemBinding

class ShoppingItemsAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingListViewModel):
    RecyclerView.Adapter<ShoppingItemsAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemName.text = item.firstName
        holder.binding.itemAmount.text = "${item.amount}"

        holder.binding.add.setOnClickListener {
            item.amount++
            viewModel.insertOrReplace(item)
        }

        holder.binding.minus.setOnClickListener {
            item.amount--
            if(item.amount > 0) {
                viewModel.insertOrReplace(item)
            } else viewModel.remove(item)
        }

        holder.binding.delete.setOnClickListener {
            viewModel.remove(item)
        }
    }

    override fun getItemCount() = items.size

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)
}