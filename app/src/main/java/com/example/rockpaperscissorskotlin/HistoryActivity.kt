package com.example.rockpaperscissorskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {


    private var results: MutableList<Result> = mutableListOf()
    private val resultsAdapter = ResultAdapter(results)
    private lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        resultRepository = ResultRepository(this)
        getResultsFromDatabase()
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvResults.layoutManager = GridLayoutManager(this@HistoryActivity, 1)
        rvResults.adapter = resultsAdapter
    }


    private fun getResultsFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            results.clear()
            results.addAll(withContext(Dispatchers.IO) {
                resultRepository.getAllReminders()
            })
            resultsAdapter.notifyDataSetChanged()
        }
    }
}
