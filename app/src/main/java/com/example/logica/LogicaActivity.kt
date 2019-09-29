package com.example.logica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class LogicaActivity : AppCompatActivity() {

    private var waar: String = "T"
    private var onwaar: String = "F"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener { onClick() }
    }

    /**
     * Calls  and checks if the answer is correct.
     */
    private fun onClick() {
        if (editText1.text.toString()==waar && editText2.text.toString()==onwaar && editText3.text.toString()==onwaar && editText4.text.toString()==onwaar) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    /**
     * Displays a successful Toast message.
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }
}
