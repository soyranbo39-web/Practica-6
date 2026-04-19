package com.example.practica6

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TimePickerDialogFragment : DialogFragment() {

    private var listener: OnTimeSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnTimeSelectedListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = requireArguments().getInt(ARG_HOUR)
        val minute = requireArguments().getInt(ARG_MINUTE)

        return TimePickerDialog(requireContext(), { _, h, m ->
            listener?.onTimeSelected(h, m)
        }, hour, minute, true)
    }

    interface OnTimeSelectedListener {
        fun onTimeSelected(hour: Int, minute: Int)
    }

    companion object {
        const val TAG = "TimePickerDialog"
        private const val ARG_HOUR = "arg_hour"
        private const val ARG_MINUTE = "arg_minute"

        fun newInstance(hour: Int, minute: Int): TimePickerDialogFragment {
            val fragment = TimePickerDialogFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_HOUR, hour)
                putInt(ARG_MINUTE, minute)
            }
            return fragment
        }
    }
}
