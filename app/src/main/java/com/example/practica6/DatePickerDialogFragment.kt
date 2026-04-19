package com.example.practica6

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DatePickerDialogFragment : DialogFragment() {

    private var listener: OnDateSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnDateSelectedListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = requireArguments().getInt(ARG_YEAR)
        val month = requireArguments().getInt(ARG_MONTH)
        val day = requireArguments().getInt(ARG_DAY)

        return DatePickerDialog(requireContext(), { _, y, m, d ->
            listener?.onDateSelected(y, m, d)
        }, year, month, day)
    }

    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, day: Int)
    }

    companion object {
        const val TAG = "DatePickerDialog"
        private const val ARG_YEAR = "arg_year"
        private const val ARG_MONTH = "arg_month"
        private const val ARG_DAY = "arg_day"

        fun newInstance(year: Int, month: Int, day: Int): DatePickerDialogFragment {
            val fragment = DatePickerDialogFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_YEAR, year)
                putInt(ARG_MONTH, month)
                putInt(ARG_DAY, day)
            }
            return fragment
        }
    }
}
