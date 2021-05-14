package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private var curPosition: Int = 1
    private var questions: ArrayList<QuestionModel>? = null
    private var selPosition: Int = 0
    private var numOfCorAns: Int = 0
    private var useHint:Int=0
    private lateinit var showAnswer:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questions=QuestionList.getQuestions()
        showAnswer=findViewById(R.id.show_answer)
        setQuestion()


            showAnswer.setOnClickListener {
                if(useHint<3){
                    UseHint()
                }
                else{
                 val dialog=AlertDialog.Builder(this@QuizActivity)
                    dialog.setTitle("Warning!")
                    dialog.setMessage("You've lost all your chances")
                    dialog.setNegativeButton("OK"){dialog, id ->
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }


        trueOption.setOnClickListener {
           selPosition = 1
            nextQuestion()
        }

        falseOption.setOnClickListener {
            selPosition=2
            nextQuestion()
        }

    }
    private fun nextQuestion(){
            hint.isVisible=false
            val question = questions?.get(curPosition - 1)

            if (question!!.correctAnswer == selPosition) {
                numOfCorAns++
            }
            curPosition++

            when {

                curPosition <= questions!!.size -> {
                    setQuestion()
                }
                else -> {
                    val intent =
                        Intent(this@QuizActivity, ResultActivity::class.java)
                    intent.putExtra(QuestionList.CORRECT_ANSWERS, numOfCorAns)
                    startActivity(intent)
                    finish()
                }
            }}

    private fun setQuestion() {

        val question =
           questions!!.get(curPosition - 1)
        questionText.text = question.question
        trueOption.text = question.FirstOption
        falseOption.text = question.SecondOption
    }

    private fun UseHint(){
        useHint++
        hint.isVisible=true
        val question =
            questions!!.get(curPosition - 1)
        hint.setText("$useHint/3 ${question.hint}")

    }


}