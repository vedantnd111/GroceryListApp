package com.example.grocerylistapp.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, val onAddButtonClicked: (ShoppingItem)->Unit): AppCompatDialog(context)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val binding = DialogAddShoppingItemBinding.inflate(LayoutInflater.from(context))
        binding.dialogAdd.setOnClickListener {
            val name = binding.dialogItemName.toString()
            val amount = binding.dialogItemAmount.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val item = ShoppingItem(name, amount.toInt())
            onAddButtonClicked(item)
        }
    }
}
