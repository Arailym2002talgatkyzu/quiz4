package com.example.quizapp

data class QuestionModel(    val id: Int,
                             val question: String,
                             val FirstOption: String,
                             val SecondOption: String,
                             val correctAnswer: Int,
                             val hint: String
                                    )

