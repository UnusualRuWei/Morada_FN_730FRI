package com.example.a0906_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MyAdapter(context: Context, private val items: MutableList<Item>) :
    ArrayAdapter<Item>(context, 0, items) {

    private var lastClickTime: Long = 0
    private val doubleClickInterval: Long = 300

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        item?.let {
            checkBox.isChecked = it.isChecked
            imageView.setImageResource(it.imageResId)
            textView.text = it.text
        }

        view.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime < doubleClickInterval) {
                showOptionsDialog(position)
            }
            lastClickTime = currentTime
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            item?.isChecked = isChecked
        }

        return view
    }

    private fun showOptionsDialog(position: Int) {
        items[position]
        val options = arrayOf("Edit", "Delete")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose an action")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> showEditDialog(position)
                1 -> deleteItem(position)
            }
        }
        builder.show()
    }

    private fun showEditDialog(position: Int) {
        val item = items[position]
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit Item")

        val editText = EditText(context).apply {
            setText(item.text)
        }
        builder.setView(editText)

        builder.setPositiveButton("OK") { _, _ ->
            item.text = editText.text.toString()
            notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }
}
