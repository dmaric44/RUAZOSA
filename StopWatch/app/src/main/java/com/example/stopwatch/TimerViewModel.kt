package com.example.stopwatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {
    var timerModel = MutableLiveData<TimerModel>()
}