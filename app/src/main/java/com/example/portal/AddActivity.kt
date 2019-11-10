package com.example.portal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*


const val EXTRA_REMINDER = "EXTRA_REMINDER"

class AddActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { onSaveClick()
        }
    }


    private fun onSaveClick() {
        if (etAddTitle.text.toString().isNotBlank() && etAddUrl.text.toString().isNotBlank()) {
            val portal = Portal(etAddTitle.text.toString(),etAddUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_REMINDER, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"The name cannot be empty"
                , Toast.LENGTH_SHORT).show()
        }
    }



}
