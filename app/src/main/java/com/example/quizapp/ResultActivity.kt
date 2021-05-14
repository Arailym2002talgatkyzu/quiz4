package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctAnswers = intent.getIntExtra(QuestionList.CORRECT_ANSWERS, 0)

        score.text = "Your Score is $correctAnswers out of 8"

        goToMain.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }
}