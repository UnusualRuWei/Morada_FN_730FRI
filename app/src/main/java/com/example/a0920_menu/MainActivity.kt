package com.example.a0920_menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "0920_Menu"
        supportActionBar?.subtitle = "4th Coding Activity"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_first -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu_dialog -> {
                CustomDialogFragment().show(supportFragmentManager, "CustomDialog")
                true
            }
            R.id.submenu_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

