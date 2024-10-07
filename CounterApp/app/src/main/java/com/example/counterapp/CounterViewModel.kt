package com.example.counterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val _repository: CounterRepository = CounterRepository()
    private var _counter = mutableIntStateOf(_repository.getCountCounter().count)

    val counter : MutableState<Int> = _counter

    fun increment() {
        _repository.increment()
        _counter.intValue = _repository.getCountCounter().count
    }

    fun decrement() {
        _repository.decrement()
        _counter.intValue = _repository.getCountCounter().count
    }

}