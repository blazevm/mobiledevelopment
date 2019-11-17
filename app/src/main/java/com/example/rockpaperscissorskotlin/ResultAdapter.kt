package com.example.rockpaperscissorskotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.result.*
import kotlinx.android.synthetic.main.result.view.*

public class ResultAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.result, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {
            if(result.messageWinner == Win.WIN){
                itemView.resultText.text = "You win!"
            } else if (result.messageWinner == Win.LOSE){
                itemView.resultText.text = "Computer wins!"
            } else if (result.messageWinner == Win.DRAW){
                itemView.resultText.text = "Draw"
            }

            if(result.playerAnswer == Move.ROCK){
                itemView.playerImage.setImageResource(R.drawable.rock)
            } else if (result.playerAnswer == Move.PAPER){
                itemView.playerImage.setImageResource(R.drawable.paper)
            } else if (result.playerAnswer == Move.SCISSORS){
                itemView.playerImage.setImageResource(R.drawable.scissors)
            }

            if(result.computerAnswer == Move.ROCK){
                itemView.computerImage.setImageResource(R.drawable.rock)
            } else if (result.computerAnswer == Move.PAPER){
                itemView.computerImage.setImageResource(R.drawable.paper)
            } else if (result.computerAnswer == Move.SCISSORS){
                itemView.computerImage.setImageResource(R.drawable.scissors)
            }

            itemView.date.text = result.date.toString()

        }
    }
}