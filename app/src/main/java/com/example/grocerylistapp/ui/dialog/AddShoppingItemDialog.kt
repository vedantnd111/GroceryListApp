package com.example.grocerylistapp.ui.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.entities.ShoppingItem
import com.example.grocerylistapp.databinding.DialogAddShoppingItemBinding
import kotlinx.android.synthetic.main.dialog_add_shopping_item.dialogAdd
import kotlinx.android.synthetic.main.dialog_add_shopping_item.dialogCancel
import kotlinx.android.synthetic.main.dialog_add_shopping_item.dialogItemAmount
import kotlinx.android.synthetic.main.dialog_add_shopping_item.dialogItemName

class AddShoppingItemDialog(context: Context, val onAddButtonClicked: (ShoppingItem)->Unit): AppCompatDialog(context)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        dialogAdd.setOnClickListener {
            val name = dialogItemName.text.toString()
            val amount = dialogItemAmount.text.toString().toInt()

            if(name.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Log.d("####", "name: $name")
            Log.d("####", "amount: $amount")
            val item = ShoppingItem(name, amount.toInt())
            onAddButtonClicked(item)
            dismiss()
        }

        dialogCancel.setOnClickListener { cancel() }
    }
}
