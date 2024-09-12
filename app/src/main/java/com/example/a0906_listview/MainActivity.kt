package com.example.a0906_listview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: MyAdapter
    private val items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val editText: EditText = findViewById(R.id.editText)
        val addButton: Button = findViewById(R.id.addButton)

        itemAdapter = MyAdapter(this, items)
        listView.adapter = itemAdapter

        addButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotBlank()) {
                val newItem = Item(false, text, R.drawable.task_icon)
                items.add(newItem)
                itemAdapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }
    }
}

