package com.loureiro.msu.geoquiz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.loureiro.msu.geoquiz2.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

@Suppress("IMPLICIT_CAST_TO_ANY")
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val quizViewModel:QuizViewModel by viewModels()

    /*private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0*/

    private var correct = 0
    private var incorrect = 0
    private var percentCorrect = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")   //Making a reference to view model so data will persist past on destroy


        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
            //questionBank[currentIndex].answered = true
        }


        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            binding.trueButton.isEnabled = false                  //Turns the buttons off after an answer is selected
            binding.falseButton.isEnabled = false
            //questionBank[currentIndex].answered = true
        }

        binding.nextButton.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()

            //questionAnswered(currentIndex)
            updateQuestion()

        }

        updateQuestion()

        binding.previousButton.setOnClickListener {
            //currentIndex = (currentIndex - 1) % questionBank.size
            quizViewModel.goBack()

            //questionAnswered(currentIndex)
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            //questionAnswered(currentIndex)
            updateQuestion()
        }

        binding.resetButton.setOnClickListener{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }

    }

    private fun updateQuestion() {
        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer)
        {
            R.string.correct_toast
            //correct +1
        }
        else
        {
            R.string.incorrect_toast
            //incorrect +1
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
           .show()

    }

    private fun questionAnswered(index: Int) {
        //val isQuestionAnswered = questionBank[index].answered
        //binding.trueButton.isEnabled = !isQuestionAnswered         //When the buttons are enabled the question has not been answered
        //binding.falseButton.isEnabled = !isQuestionAnswered

    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
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