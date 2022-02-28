package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(TimerViewModel::class.java)
        viewModel.timerModel.observe(this, Observer { timerModel ->
            secondsPassedTextId.text = timerModel.passedTime
        })
        viewModel.timerModel.value = TimerModel()

        val timer = Timer(viewModel)

        startButtonId.setOnClickListener {
            timer.start()
        }
        stopButtonId.setOnClickListener {
            timer.stop()
        }
        pauseButtonId.setOnClickListener {
            timer.pause()
        }

    }
}