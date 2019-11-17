package com.example.rockpaperscissorskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultRepository = ResultRepository(this)
        initViews()
        getResultsFromDatabase()
    }

    public fun onHistory(item: MenuItem)  {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivityForResult(intent, 100)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true
    }

    private fun initViews() {
        btnRock.setOnClickListener { onRockClick() }
        btnPaper.setOnClickListener { onPaperClick() }
        btnScissors.setOnClickListener { onScissorsClick() }
    }

    private fun play(playerMove: Move) {
        // random toewijzging plaatje computer
        var computerMove: Move = Move.values()[(0..2).random()]
        //if voor spel welke wint
        var win: Win = Win.DRAW

        if (playerMove == Move.ROCK && computerMove == Move.SCISSORS) {
            win = Win.WIN
        } else if (playerMove == Move.SCISSORS && computerMove == Move.PAPER) {
            win = Win.WIN
        } else if (playerMove == Move.PAPER && computerMove == Move.ROCK) {
            win = Win.WIN
        } else if (computerMove == Move.ROCK && playerMove == Move.SCISSORS) {
            win = Win.LOSE
        } else if (computerMove == Move.SCISSORS && playerMove == Move.PAPER) {
            win = Win.LOSE
        } else if (computerMove == Move.PAPER && playerMove == Move.ROCK) {
            win = Win.LOSE
        }
        //data/tijd opslaan per spel
        var datum: Date = Date()
        //tekst printen bij winnaar
        if(win == Win.WIN){
            resultText.text = "You win!"
        } else if (win == Win.LOSE){
            resultText.text = "Computer wins!"
        } else if (win == Win.DRAW){
            resultText.text = "Draw"
        }
        //plaatje keuze computer + speler printen
        if(playerMove == Move.ROCK){
            playerImage.setImageResource(R.drawable.rock)
        } else if (playerMove == Move.PAPER){
            playerImage.setImageResource(R.drawable.paper)
        } else if (playerMove == Move.SCISSORS){
            playerImage.setImageResource(R.drawable.scissors)
        }

        if(computerMove == Move.ROCK){
            computerImage.setImageResource(R.drawable.rock)
        } else if (computerMove == Move.PAPER){
            computerImage.setImageResource(R.drawable.paper)
        } else if (computerMove == Move.SCISSORS){
            computerImage.setImageResource(R.drawable.scissors)
        }

        incResult.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                resultRepository.insertReminder(Result(computerMove,playerMove,win,datum))
            }
            getResultsFromDatabase()
        }
    }

    // aantal wins, draw, verliezen tellen
    private fun getResultsFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val results = withContext(Dispatchers.IO) {
                resultRepository.getAllReminders()
            }
            var wins: Int = 0
            var losses: Int = 0
            var draws: Int = 0

            for (result in results) {
                if (result.messageWinner == Win.WIN){
                    wins += 1
                } else if (result.messageWinner == Win.LOSE){
                    losses += 1
                } else if (result.messageWinner == Win.DRAW){
                    draws += 1
                }

            }

            resultCount.text = "Win: $wins  Draw: $draws  Lose: $losses"
        }
    }

    private fun onScissorsClick() {
        play(Move.SCISSORS)
    }

    private fun onPaperClick() {
        play(Move.PAPER)
    }

    private fun onRockClick() {
        play(Move.ROCK)
    }

}
