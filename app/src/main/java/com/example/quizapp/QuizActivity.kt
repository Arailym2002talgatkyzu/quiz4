package com.example.quizapp

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private var curPosition: Int = 1
    private var selPosition: String =""
    private var numOfCorAns: Int = 0
    private var useHint:Int=0
    private var questionlist = ArrayList<QuestionModel>()
    private lateinit var showAnswer:Button
    private lateinit var TempDialog: ProgressDialog
    private lateinit var timer: CountDownTimer
    private var i:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        TempDialog= ProgressDialog(this@QuizActivity)
        TempDialog.setMessage("Please wait...")
        TempDialog.setCancelable(false)
        TempDialog.setProgress(i)
        TempDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        TempDialog.window?.setBackgroundDrawable(ColorDrawable(Color.GRAY))

        var dbquestions= FirebaseDatabase.getInstance().reference.child("Questions")
       /* questions=QuestionList.getQuestions()*/
        var data = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                questionlist.clear()
                for (i in snapshot.children){

                    var id1= i.key as String
                    var id=id1.toInt()
                    var question=i.child("question").getValue() as String
                    var answer=i.child("answer").getValue() as String
                    var hint = i.child("hint").getValue()as String

                    val q=
                        QuestionModel(
                            id ,
                            question,
                            answer,
                            hint
                        )
                    questionlist.add(q)


                }
               /* questions=questionList*/
                /*questionText.text=questionlist.get(0).question*/
            }
        }
        dbquestions.addValueEventListener(data)
        dbquestions.addListenerForSingleValueEvent(data)
        TempDialog.show()
        timer = object: CountDownTimer(5000, 1000){
            override fun onFinish() {
                TempDialog.dismiss()
                setQuestion()
            }

            override fun onTick(millisUntilFinished: Long) {
                TempDialog.setMessage("Please wait...")
            }

        }.start()
        showAnswer=findViewById(R.id.show_answer)
        /*setQuestion()*/



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
           selPosition = "True"
            nextQuestion()
        }

        falseOption.setOnClickListener {
            selPosition="False"
            nextQuestion()
        }

    }
    private fun nextQuestion(){
            hint.isVisible=false
            val question = questionlist?.get(curPosition - 1)

            if (question!!.answer == selPosition) {
                numOfCorAns++
            }
            curPosition++

            when {

                curPosition <= questionlist!!.size -> {
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
           questionlist.get(curPosition - 1)
        questionText.text = question.question
    }

    private fun UseHint(){
        useHint++
        hint.isVisible=true
        val question =
            questionlist!!.get(curPosition - 1)
        hint.setText("$useHint/3 ${question.hint}")

    }


}