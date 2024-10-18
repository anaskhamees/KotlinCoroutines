package com.example.stateflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

fun main() = runBlocking {
    val counterFlow = MutableStateFlow(0)

    /* Launch a coroutine to collect updates from the counterFlow*/
    launch {
        counterFlow.collect { value ->
            println("Collected Value: $value")
        }
    }

    /* Launch another coroutine to update the counterFlow every second*/
    launch {
        for (i in 1..5) {
            delay(1000)
            counterFlow.value = i
        }
    }

    delay(5000)
}
