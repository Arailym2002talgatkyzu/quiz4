package com.example.quizapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_result.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private val rnds = (1..10).random()
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val currentUser = auth.currentUser
        val correctAnswers = intent.getIntExtra(QuestionList.CORRECT_ANSWERS, 0)

        databaseReference = database?.reference!!.child("Users").child(currentUser?.uid!!)


        score.text = "Your Score is $correctAnswers out of 8"

        val currentUSerDb = database?.reference!!.child("Users").child(currentUser?.uid!!)

        /*currentUSerDb?.child("score")?.setValue("$correctAnswers/8")*/
        val now = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")
        val score="$correctAnswers/8"
        val time = now.format(formatter)
        currentUSerDb?.child(rnds.toString())?.setValue(User(score, time))

        /*currentUSerDb?.child("time")?.setValue(time)*/

        goToMain.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }

}