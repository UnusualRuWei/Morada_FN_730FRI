package com.example.a0920_menu

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Dialog Title")
                .setMessage("This is a custom dialog message.")
                .setPositiveButton("Go to First") { _, _ ->
                    val fragmentTransaction = it.supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, ProfileFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                .setNegativeButton("Exit") { _, _ ->
                    activity?.finish()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

