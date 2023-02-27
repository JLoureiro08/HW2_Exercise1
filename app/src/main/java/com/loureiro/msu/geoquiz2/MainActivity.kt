package com.loureiro.msu.geoquiz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.loureiro.msu.geoquiz2.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

@Suppress("IMPLICIT_CAST_TO_ANY")
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private var correct = 0
    private var incorrect = 0
    private var percentCorrect = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
            questionBank[currentIndex].answered = true
        }


        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            binding.trueButton.isEnabled =
                false                  //Turns the buttons off after an answer is selected
            binding.falseButton.isEnabled = false
            questionBank[currentIndex].answered = true
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            questionAnswered(currentIndex)
            updateQuestion()

        }

        updateQuestion()

        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            questionAnswered(currentIndex)
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            questionAnswered(currentIndex)
            updateQuestion()
        }

        binding.resetButton.setOnClickListener{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }

        /*binding.gradeButton.setOnClickListener {
            percentCorrect = (correct * 100) / questionBank.size
            Toast.makeText(this, percentCorrect, Toast.LENGTH_LONG)
                .show()
        }*/


    }

    private fun quizPercent(){
        if(questionBank.size == currentIndex)
        percentCorrect = (correct * 100) / questionBank.size

}


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer)
        {
            R.string.correct_toast
        }
        else
        {
            R.string.incorrect_toast
        }

        quizPercent()

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
           .show()

    }

    private fun questionAnswered(index: Int) {
        val isQuestionAnswered = questionBank[index].answered
        binding.trueButton.isEnabled = !isQuestionAnswered         //When the buttons are enabled the question has not been answered
        binding.falseButton.isEnabled = !isQuestionAnswered

    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onPause() called")
    }
     override fun onPause(){
         super.onPause()
         Log.d(TAG, "onPause() called")
     }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}