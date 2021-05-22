package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var db= FirebaseDatabase.getInstance().reference.child("Users").child((user?.uid!!))
        var data = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sb= StringBuilder()
                for (i in snapshot.children){
                    var score=i.child("score").getValue()
                    var date=i.child("time").getValue()
                    sb.append("$score    $date\n" )
                }
                result.setText(sb)
            }
        }
        db.addValueEventListener(data)
        db.addListenerForSingleValueEvent(data)
    }
}