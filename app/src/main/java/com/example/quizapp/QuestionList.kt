package com.example.quizapp

object QuestionList {
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<QuestionModel> {
        val questionList = ArrayList<QuestionModel>()

        val q1 = QuestionModel(
            1,
            "Astana IT University is located in Nur-Sultan",
            "True",
            "False",
            1,
            "True. Astana IT University is a leading competence center for digital transformation in Central Asia, located in Nur-Sultan"

        )

        val q2 = QuestionModel(
            2,
            "The totem of the Asian Winter Games which was held in 2011 was the Irbis",
            "True",
            "False",
            1,
            "True. The 2011 Asian Winter Games, officially known as the 7th Asian Winter Games. The Symbolic animal of the game was Irbis"

        )

        val q3=QuestionModel(
            3,
            "The height of the summit of Jomolungma is 7651 km",
            "True",
            "False",
            2,
            "False. Mount Everest (Jomolungma) is the highest mountain on the planet. Its officially recognized height is 8848 meters above sea level"
        )

        val q4=QuestionModel(
            4,
            "The Congo River crosses the Equator twice",
            "True",
            "False",
            1,
            "True. The Congo River has a total length of 4,370 km (2,715 mi). It is the only river to cross the equator twice"
        )
        val q5=QuestionModel(
            5,
            "The printer is an input device",
            "True",
            "False",
            2,
            "False. A printer is an external hardware output device that takes the electronic data stored on a computer or other device and generates a hard copy"
        )
        val q6=QuestionModel(
            6,
            "Lightning never strikes the same place twice.",
            "True",
            "False",
            2,
            "False. Lightning strikes the same place quite often. For example, the Empire State Building receives more than 100 lightning strikes a year"
        )
        val q7=QuestionModel(
            7,
            "The top of the Eiffel Tower tilts away from the Sun.",
            "True",
            "False",
            1,
            "True. The metal of the tower expands from the heat of the Sun, so the side that is in the Sun is always slightly larger than the opposite side, which is why the top deviates 17 cm from the Sun"
        )
        val q8=QuestionModel(
            8,
            "Adults have fewer bones than infants",
            "True",
            "False",
            1,
            "True. Many bones, such as the skull, are divided into several fragments at birth, and later merge into one bone"
        )
        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)
        questionList.add(q5)
        questionList.add(q6)
        questionList.add(q7)
        questionList.add(q8)
        return questionList
    }
}