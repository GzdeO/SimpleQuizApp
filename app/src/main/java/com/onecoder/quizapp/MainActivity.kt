package com.onecoder.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.onecoder.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions= arrayOf(
        "Tarihte Türk adıyla kurulan ilk Türk devleti hangisidir?",
        "Teoman hangi devletin kurucusudur?",
        "Sultan Alparslan hangi devletin yöneticisiydi?"
    )

    private val  options= arrayOf(
        arrayOf("İskitler","Göktürk","Türgişler"),
        arrayOf("Hunlar","Göktürkler","Selçuklular"),
        arrayOf("Batı Hunları","Selçuklular","Osmanlılar")
    )

    private val correctAnswers= arrayOf(1,0,0)

    private var currentQuestionIndex=0
    private var score=0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()
        binding.quiz1.setOnClickListener{
            checkAnswers(0)
        }
        binding.quiz2.setOnClickListener{
            checkAnswers(1)
        }
        binding.quiz3.setOnClickListener{
            checkAnswers(2)
        }
        binding.restart.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctResponse(buttonIndex: Int){
        when(buttonIndex){
            0->binding.quiz1.setBackgroundColor(Color.GREEN)
            1->binding.quiz2.setBackgroundColor(Color.GREEN)
            2->binding.quiz3.setBackgroundColor(Color.GREEN)

        }
    }

    private fun wrongResponse(buttonIndex: Int){
        when(buttonIndex){
            0->binding.quiz1.setBackgroundColor(Color.RED)
            1->binding.quiz2.setBackgroundColor(Color.RED)
            2->binding.quiz3.setBackgroundColor(Color.RED)
        }

    }

    private fun resetBottonColor(){
        binding.quiz1.setBackgroundColor(Color.rgb(50,59,96))
        binding.quiz2.setBackgroundColor(Color.rgb(50,59,96))
        binding.quiz3.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showScore(){
        Toast.makeText(this,"Your score: ${score} out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restart.isEnabled=true
    }

    private fun displayQuestion(){
        binding.questionText.text=questions[currentQuestionIndex]
        binding.quiz1.text=options[currentQuestionIndex][0]
        binding.quiz2.text=options[currentQuestionIndex][1]
        binding.quiz3.text=options[currentQuestionIndex][2]
        resetBottonColor()
        }

    private fun checkAnswers(selectedAnswerIndex: Int){
        val correctAnswerIndex=correctAnswers[currentQuestionIndex]
        if(selectedAnswerIndex.equals(correctAnswerIndex)){
            score++
            correctResponse(selectedAnswerIndex)
        }else{
            wrongResponse(selectedAnswerIndex)
            correctResponse(correctAnswerIndex)
        }

        if(currentQuestionIndex <questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        }else{
            showScore()
        }

    }

    private fun restartQuiz(){
        currentQuestionIndex=0
        score=0
        displayQuestion()
        binding.restart.isEnabled=false

    }

}