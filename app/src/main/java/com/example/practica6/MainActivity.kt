package com.example.practica6

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity(),
    DatePickerDialogFragment.OnDateSelectedListener,
    TimePickerDialogFragment.OnTimeSelectedListener {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var tvDateValue: TextView
    private lateinit var tvTimeValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDateValue = findViewById(R.id.tvDateValue)
        tvTimeValue = findViewById(R.id.tvTimeValue)

        initializeDefaultValuesIfNeeded()
        renderValues()

        findViewById<Button>(R.id.btnPickDate).setOnClickListener {
            showDatePickerDialog()
        }

        findViewById<Button>(R.id.btnPickTime).setOnClickListener {
            showTimePickerDialog()
        }
    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        viewModel.year = year
        viewModel.month = month
        viewModel.day = day
        renderValues()
    }

    override fun onTimeSelected(hour: Int, minute: Int) {
        viewModel.hour = hour
        viewModel.minute = minute
        renderValues()
    }

    private fun initializeDefaultValuesIfNeeded() {
        if (viewModel.year == 2026 && viewModel.month == 0 && viewModel.day == 1 && viewModel.hour == 12 && viewModel.minute == 0) {
            val now = Calendar.getInstance()
            viewModel.year = now.get(Calendar.YEAR)
            viewModel.month = now.get(Calendar.MONTH)
            viewModel.day = now.get(Calendar.DAY_OF_MONTH)
            viewModel.hour = now.get(Calendar.HOUR_OF_DAY)
            viewModel.minute = now.get(Calendar.MINUTE)
        }
    }

    private fun renderValues() {
        val formattedDate = String.format("%02d/%02d/%04d", viewModel.day, viewModel.month + 1, viewModel.year)
        val formattedTime = String.format("%02d:%02d", viewModel.hour, viewModel.minute)
        tvDateValue.text = getString(R.string.selected_date_value, formattedDate)
        tvTimeValue.text = getString(R.string.selected_time_value, formattedTime)
    }

    private fun showDatePickerDialog() {
        if (supportFragmentManager.findFragmentByTag(DatePickerDialogFragment.TAG) != null) return

        DatePickerDialogFragment
            .newInstance(viewModel.year, viewModel.month, viewModel.day)
            .show(supportFragmentManager, DatePickerDialogFragment.TAG)
    }

    private fun showTimePickerDialog() {
        if (supportFragmentManager.findFragmentByTag(TimePickerDialogFragment.TAG) != null) return

        TimePickerDialogFragment
            .newInstance(viewModel.hour, viewModel.minute)
            .show(supportFragmentManager, TimePickerDialogFragment.TAG)
    }
}
