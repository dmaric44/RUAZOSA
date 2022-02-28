package com.example.stopwatch

import kotlinx.coroutines.*

class Timer(viewModel: TimerViewModel) {
    var seconds: Int = 0
    var startwatchJob: Job? = null
    var isCountingStarted: Boolean = false
    var viewModel: TimerViewModel? = null
    var timerModel: TimerModel? = null

    init {
        this.viewModel = viewModel
        timerModel = viewModel.timerModel.value
    }

    fun reset(){
        seconds = 0
        isCountingStarted = false
    }

    fun start(){
        if(!isCountingStarted) {
            reset()
            isCountingStarted = true
            seconds = 0
            startTimingJob()
        }
        else{
            startTimingJob()
        }
    }

    fun stop(){
        startwatchJob?.cancel()
        isCountingStarted = false
    }

    fun pause(){
        startwatchJob?.cancel()
    }


    private fun startTimingJob(){
        startwatchJob = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(1000)
                seconds += 1
                withContext(Dispatchers.Main){
                    timerModel?.passedTime = seconds.toString()
                    viewModel?.timerModel?.value = timerModel
                }
            }
        }
    }
}