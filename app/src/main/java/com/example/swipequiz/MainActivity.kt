package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        questions.add(Question("A straight line measures 180 degrees", true))
        questions.add(Question("Spiders belong to the \"insect\" class of animals", false))
        questions.add(
            Question(
                "Veins look blue because the oxygen-depleted blood in them is blue",
                false
            )
        )
        questions.add(Question("Volleyball was invented as a game for businessmen", true))
        questionAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                checkAnswer(questions[position], direction)
                questions.removeAt(position)
                questionAdapter.notifyItemRemoved(position)
                println(direction)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun checkAnswer(question: Question, direction: Int) {
        if (question.answer && direction == 4) {
            Snackbar.make(
                textView,
                "Correct! The answer was: ${question.answer}",
                Snackbar.LENGTH_SHORT
            ).show()
        } else if (!question.answer && direction == 8) {
            Snackbar.make(
                textView,
                "Correct! The answer was: ${question.answer}",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                textView,
                "Wrong! The answer was: ${question.answer}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }


}
