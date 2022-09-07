package com.gauravbora.javatrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gauravbora.javatrivia.R
import com.gauravbora.javatrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>)

    // first answer is correct +  We randomize the answers before showing the text.

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Which provides runtime environment for java byte code to be executed?",
            answers = listOf("JVM", "JDK", "JRE", "JAVAC")),
        Question(text = "Which of the following are not Java keywords ?",
            answers = listOf("then", "switch", "double", "instanceof")),
        Question(text = "Which of these have highest precedence?",
            answers = listOf("()", "++", "*", ">>")),
        Question(text = "Data type long literals are appended by _____",
            answers = listOf("Both A and B", "Lowercase L", "Uppercase L", "Long")),
        Question(text = "Java language was initially called as ________",
            answers = listOf("Oak", "Sumatra", "J++", "Pine")),
        Question(text = "Which one is a template for creating different objects ?",
            answers = listOf("A class", "An array", "Interface", "Method")),
        Question(text = "Which of these is not a bitwise operator?",
            answers = listOf("<=' Operator", "&' Operator", "&=' Operator", "|=' Operator")),
        Question(text = "Modulus operator (%) can be applied to which of these?",
            answers = listOf("Both A and B", "Floating - point numbers", "Integer", "None Of These")),
        Question(text = "What feature of OOP has a super-class sub-class concept?",
            answers = listOf("Hierarchical inheritance", "Single inheritance", "Multiple inheritances", "Multilevel inheritance")),
        Question(text = "What is the full form of JVM ?",
            answers = listOf("Java Virtual Machine", "Java Verified Machine", "Java Very Large Machine", "None")),
        Question(text = "Which of the following are not Java modifiers?",
        answers = listOf("friendly", "public", "private", "transient")),
        Question(text = "What is the size of float and double in java?",
            answers = listOf("32 and 64", "32 and 32", "64 and 64", "64 and 32")),
        Question(text = "Automatic type conversion is possible in which of the possible cases?",
            answers = listOf("int to long", "long to int", "byte to int", "short to int")),
        Question(text = "When an array is passed to a method, what does the method receive?",
            answers = listOf("The reference tof the Array", " A copy of the Array", "Length of the Array", "None")),
        Question(text = "Arrays in java are-",
            answers = listOf("Objects", "Object References", "Primitive data type", "None")),
        Question(text = "When is the object created with new keyword?",
            answers = listOf("At run time", "At compile time", "Depends on the code", "None")),
        Question(text = "In which of the following is toString() method defined?",
            answers = listOf("java.lang.Object", "java.lang.String", "java.lang.util", "None")),
        Question(text = "compareTo() returns",
            answers = listOf("An int value", "True", "False", "None")),
        Question(text = "Output of Math.floor(3.6)?",
            answers = listOf("3.0", "3", "4", "4.0")),
        Question(text = "Identify the modifier which cannot be used for constructor.",
            answers = listOf("static", "public", "private", "protected")),
        Question(text = "When is the finalize() method called?",
            answers = listOf("Before garbage collection", "None", "before an object goes out of space", "before a variable goes out of scope")),
        Question(text = "What is Runnable?",
            answers = listOf("Interface", "Class", "Method", "Abstract class")),
        Question(text = "Which of the following is used to find and fix bugs in the program?",
            answers = listOf("JDB", "JVM", "JRE", "JDK"))


    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 15

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
//                        safeargs
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestions,questionIndex))

                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment2())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_java_trivia_question, questionIndex + 1, numQuestions)
    }
}