package com.example.practica6

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    var year: Int
        get() = state[KEY_YEAR] ?: 2026
        set(value) {
            state[KEY_YEAR] = value
        }

    var month: Int
        get() = state[KEY_MONTH] ?: 0
        set(value) {
            state[KEY_MONTH] = value
        }

    var day: Int
        get() = state[KEY_DAY] ?: 1
        set(value) {
            state[KEY_DAY] = value
        }

    var hour: Int
        get() = state[KEY_HOUR] ?: 12
        set(value) {
            state[KEY_HOUR] = value
        }

    var minute: Int
        get() = state[KEY_MINUTE] ?: 0
        set(value) {
            state[KEY_MINUTE] = value
        }

    companion object {
        private const val KEY_YEAR = "year"
        private const val KEY_MONTH = "month"
        private const val KEY_DAY = "day"
        private const val KEY_HOUR = "hour"
        private const val KEY_MINUTE = "minute"
    }
}
